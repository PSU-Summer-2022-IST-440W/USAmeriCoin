package edu.psu.ist440.USAmeriCoin.controller;

import edu.psu.ist440.USAmeriCoin.model.User;
import edu.psu.ist440.USAmeriCoin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * ApiController
 * Manages all REST API interactions for external integrations
 * IST 440: IST Integration and Problem Solving
 * USAmeriCoin - Team 5
 * Date Created: Jun 6, 2022
 */

@Controller
public class ApiController {
    @Autowired
    private UserService userService;

    /**
     * api route "/reports"
     * Homepage of the reports and dashboards screen
     */
    @GetMapping("/api/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable(value = "userId") long userId) {
        User thisUser = userService.getUserById(10);
        return new ResponseEntity<User>(thisUser, HttpStatus.OK);
    }
}

