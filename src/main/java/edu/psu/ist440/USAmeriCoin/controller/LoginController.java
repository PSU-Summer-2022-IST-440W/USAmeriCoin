package edu.psu.ist440.USAmeriCoin.controller;

import edu.psu.ist440.USAmeriCoin.model.User;
import edu.psu.ist440.USAmeriCoin.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
    public String login(@ModelAttribute("currentUser") User userAttempted, HttpSession session) {
        session.setAttribute("currentUser", null);
        User user = userService.getUserByUsername(userAttempted.getUsername());
        if (user != null) {
            System.out.println("Att: " + userAttempted.getPassword());
            if (user.getPassword().equals(userAttempted.getPassword())) {
                session.setAttribute("authUserId", user.getUserId());
                session.setAttribute("isAuth", true);
                session.setAttribute("isUser", user.isUser());
                session.setAttribute("isAdministrator", user.isAdministrator());
                return "redirect:/";
            }
            else {
                return "redirect:/login?retry=1";
            }
        }
        else {
            return "redirect:/login?retry=1";
        }
    }

}

