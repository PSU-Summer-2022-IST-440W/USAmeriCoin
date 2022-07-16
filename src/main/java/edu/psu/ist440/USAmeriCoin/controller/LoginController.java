package edu.psu.ist440.USAmeriCoin.controller;

import com.duosecurity.Client;
import com.duosecurity.exception.DuoException;
import com.duosecurity.model.Token;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.psu.ist440.USAmeriCoin.model.User;
import edu.psu.ist440.USAmeriCoin.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * LoginController
 * Controls the user login and session management processes
 * IST 440: IST Integration and Problem Solving
 * USAmeriCoin - Team 5
 * Date Created: Jun 6, 2022
 */

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @Value("${duo.api.host}")
    private String apiHost;

    @Value("${duo.clientId}")
    private String clientId;

    @Value("${duo.clientSecret}")
    private String clientSecret;

    @Value("${duo.redirect.uri}")
    private String redirectUri;

    @Value("${duo.failmode}")
    private String failmode;

    private Client duoClient;
    private Map<String, String> stateMap;

    @PostConstruct
    public void initializeDuoClient() throws DuoException {
        stateMap = new HashMap<>();
        duoClient = new Client.Builder(clientId, clientSecret, apiHost, redirectUri).build();
    }

    @GetMapping("/duo-callback")
    public ModelAndView duoCallback(@RequestParam("duo_code") String duoCode,
                                    @RequestParam("state") String state, HttpSession session) throws DuoException {
        // Step 5: Validate state returned from Duo is the same as the one saved previously.
        // If it isn't return an error
        if (!stateMap.containsKey(state)) {
            ModelAndView model = new ModelAndView("/login");
            model.addObject("message", "Session Expired");
            return model;
        }
        // Remove state from the list of valid sessions
        String username = stateMap.remove(state);

        // Step 6: Exchange the auth duoCode for a Token object
        Token token = duoClient.exchangeAuthorizationCodeFor2FAResult(duoCode, username);

        // If the auth was successful, render the welcome page otherwise return an error
        if (authWasSuccessful(token)) {
            User authUser = userService.getUserByUsername(username);
            ModelAndView model = new ModelAndView("/index");
            model.addObject("token", tokenToJson(token));
            session.setAttribute("authUserId", authUser.getUserId());
            session.setAttribute("isAuth", true);
            session.setAttribute("isUser", authUser.isUser());
            session.setAttribute("isAdministrator", authUser.isAdministrator());

            return model;
        } else {
            ModelAndView model = new ModelAndView("/login");
            model.addObject("message", "2FA Failed");
            return model;
        }
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("isAuth") != null)
            if ((boolean)session.getAttribute("isAuth")) {
                model.addAttribute("pageTitle", "Welcome to USAmeriCoin");
                return "index";
            } else {
                return "redirect:/login";
            }
        else
            return "redirect:/login";
    }

    /**
     * login route "/login"
     * Sets up the UI for a user login attempt
     */
    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        User user = new User();
        model.addAttribute("pageTitle", "Login to USAmeriCoin");
        model.addAttribute("user", user);
        model.addAttribute("message", "");
        return "login";
    }

    /**
     * login route "/login"
     * resets all session variables when a user logs out of the system
     */
    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        session.setAttribute("authUserId", null);
        session.setAttribute("isAuth", false);
        session.setAttribute("isUser", false);
        session.setAttribute("isAdministrator", false);
        return "redirect:/login";
    }

    /**
     * login route "/login"
     * POST-processing for a user login attempt. If successful, session variables are set.
     */
    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("currentUser") User userAttempted, HttpSession session) throws DuoException {
        session.setAttribute("currentUser", null);
        User user = userService.getUserByUsername(userAttempted.getUsername());
        ModelAndView model = new ModelAndView("/index");

        if (user != null) {
            System.out.println("Att: " + userAttempted.getPassword());
            if (user.getPassword().equals(userAttempted.getPassword())) {
                try {
                    duoClient.healthCheck();
                } catch (DuoException exception) {
                    // If the health check failed AND the integration is configured to fail open then render
                    // the welcome page.  If the integarion is configured to fail closed return an error
                    if ("OPEN".equalsIgnoreCase(failmode)) {
                        model.addObject("token", "Login Successful, but 2FA Not Performed."
                                + "Confirm application.properties values are correct and that Duo is reachable");
                        return model;
                    } else {
                        model.addObject("message", "2FA Unavailable."
                                + "Confirm application.properties values are correct and that Duo is reachable");
                    }
                }

                // Step 3: Generate and save a state variable
                String state = duoClient.generateState();
                // Store the state to remember the session and username
                stateMap.put(state, userAttempted.getUsername());

                // Step 4: Create the authUrl and redirect to it
                String authUrl = duoClient.createAuthUrl(userAttempted.getUsername(), state);
                model = new ModelAndView("redirect:" + authUrl);
                return model;
            }
            else {
                model = new ModelAndView("redirect:/login");
            }
        }
        else {
            model = new ModelAndView("redirect:/login");
        }
        return model;
    }

    private static String tokenToJson(Token token) throws DuoException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(token);
        } catch (JsonProcessingException jpe) {
            throw new DuoException("Could not convert token to JSON");
        }
    }
    private boolean authWasSuccessful(Token token) {
        if (token != null && token.getAuth_result() != null) {
            return "ALLOW".equalsIgnoreCase(token.getAuth_result().getStatus());
        }
        return false;
    }
}

