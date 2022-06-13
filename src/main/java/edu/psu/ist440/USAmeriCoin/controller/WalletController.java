package edu.psu.ist440.USAmeriCoin.controller;

import edu.psu.ist440.USAmeriCoin.model.*;
import edu.psu.ist440.USAmeriCoin.service.WalletService;
import edu.psu.ist440.USAmeriCoin.service.UserService;

import edu.psu.ist440.USAmeriCoin.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;

/**
 * WalletController
 * Manages all the user interactions with the wallets of the system
 * IST 440: IST Integration and Problem Solving
 * USAmeriCoin - Team 5
 * Date Created: Jun 6, 2022
 */

@Controller
public class WalletController {
    @Autowired
    private UserService userService;
    @Autowired
    private WalletService walletService;


    /**
     * wallets route "/editWallet/{walletId}"
     * Allows for the wallet shell to be updated
     */
    @GetMapping("/wallets")
    public String walletList(Model model, HttpSession session) {
        model.addAttribute("walletList",  this.userService.getUserById((long)session.getAttribute("authUserId")).getUserWallets());
        model.addAttribute("cryptoList",  this.walletService.getAllCryptocurrencies());
        return "wallet_list";
    }

    /**
     * editWallet route "/editWallet/{walletId}"
     * Allows for the wallet shell to be updated
     */
    @GetMapping("/wallets/{walletId}")
    public String walletDetail(@PathVariable(value = "walletId") long walletId, Model model, HttpSession session) {
        model.addAttribute("wallet",  this.walletService.getWalletById(walletId));
        model.addAttribute("cryptoList",  this.walletService.getAllCryptocurrencies());
        return "wallet_edit";
    }

    /**
     * walletStatus route "/saveWallet"
     * The POST-processing of the above walletStatus updates
     */
    @PostMapping(value="/saveWallet",params="SaveWallet")
    public String saveWallet(@ModelAttribute("wallet") Wallet wallet) {
        this.walletService.saveWallet(wallet);
        return "redirect:/walletStatus";
    }

}

