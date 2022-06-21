package edu.psu.ist440.USAmeriCoin.controller;

import edu.psu.ist440.USAmeriCoin.model.User;
import edu.psu.ist440.USAmeriCoin.model.Password;
import edu.psu.ist440.USAmeriCoin.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;

/**
 * UserController
 * Manages all the user account updates for the system
 * IST 440: IST Integration and Problem Solving
 * USAmeriCoin - Team 5
 * Date Created: Jun 6, 2022
 */


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * editUser route "/editUser/{userId}"
     * Displays the passed-in user account information for updating
     */
    @GetMapping("/editUser/{userId}")
    public String user(@PathVariable(value = "userId") long userId, Model model, HttpSession session) {
        model.addAttribute("allRoles", userService.getAllRoles());
        model.addAttribute("user", this.userService.getUserById(userId));
        model.addAttribute("pass", this.userService.getPasswordById(userId));
        return "user";
    }

    /**
     * editUser route "/editUser"
     * Displays the current user account information for updating
     */
    @GetMapping("/editUser")
    public String user(Model model, HttpSession session) {
        model.addAttribute("pageTitle", "Edit User Profile");
        model.addAttribute("allRoles", userService.getAllRoles());
        model.addAttribute("user", this.userService.getUserById((long)session.getAttribute("authUserId")));
        model.addAttribute("pass", this.userService.getPasswordById((long)session.getAttribute("authUserId")));
        return "user";
    }
    /**
     * editUser route "/editUser"
     * POST-processing for the user account information updates
     */
    @PostMapping("/editUser")
    public String user(@ModelAttribute("user") User user, HttpSession session) {
        this.userService.saveUser(user);
        return "redirect:/editUser/" + user.getUserId();
    }

    /**
     * passwordUpdate route "/password"
     * POST-processing for the password portion of the user account update
     */
    @PostMapping("/password")
    public String passwordUpdate(@ModelAttribute("pass") Password pass, HttpSession session) {
        this.userService.savePassword(pass);
        return "redirect:/editUser"  + pass.getUserId();
    }

    /**
     * createAccount routes
     * POST-processing for the new account process
     */
    @PostMapping("/createAccount")
    public String createAccount(User user, HttpSession session) {
        this.userService.saveUser(user);
        return "redirect:/editUser/" + user.getUserId();
    }

    /**
     * createAccount route
     * Sets up for creating a new USAmeriCoin user account
     */
    @GetMapping("/createAccount")
    public String createAccount(Model model, HttpSession session){
        User user = new User();
        model.addAttribute("pageTitle", "Create a USAmeriCoin User Account");
        model.addAttribute("user", user);
        return "create_account";
    }

    /**
     * userList route
     * Sets up for creating a new USAmeriCoin user account
     */
    @GetMapping("/userList")
    public String userList(Model model, HttpSession session){
        model.addAttribute("pageTitle", "Administration User List");
        ArrayList<User> usersToList = new ArrayList<>();
        usersToList.addAll(this.userService.getAllUsers());
        Collections.sort(usersToList,(u1, u2) -> u1.toString().compareTo(u2.toString()));
        model.addAttribute("userList", usersToList);
        return "user_list";
    }
}

