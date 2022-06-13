package edu.psu.ist440.USAmeriCoin.controller;

import edu.psu.ist440.USAmeriCoin.model.Wallet;
import edu.psu.ist440.USAmeriCoin.model.*;
import edu.psu.ist440.USAmeriCoin.model.ReportKPI;
import edu.psu.ist440.USAmeriCoin.model.User;
import edu.psu.ist440.USAmeriCoin.service.WalletService;
import edu.psu.ist440.USAmeriCoin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;

/**
 * ReportController
 * Manages all the user interactions with the reporting and analytics within USAmeriCoin
 * IST 440: IST Integration and Problem Solving
 * USAmeriCoin - Team 5
 * Date Created: Jun 6, 2022
 */

@Controller
public class DashboardController {
    @Autowired
    private UserService userService;
    @Autowired
    private WalletService walletService;

    /**
     * reports route "/reports"
     * Homepage of the reports and dashboards screen
     */
    @GetMapping("/reports")
    public String reportsHome(Model model, HttpSession session) {
        ReportKPI reportKpi = new ReportKPI();

        ArrayList<Wallet> userWallets = new ArrayList<>();

        User thisUser = this.userService.getUserById((long)session.getAttribute("authUserId"));

        userWallets.addAll(thisUser.getUserWallets());

        model.addAttribute("userWallets", userWallets);
        return "dashboard";
    }
}

