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
import java.util.Set;

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
        model.addAttribute("pageTitle", "Account Wallets");
        Set<Wallet> userWallets = this.userService.getUserById((long)session.getAttribute("authUserId")).getUserWallets();
        Double accountAmountUsd = 0.0;
        for (Wallet thisWallet : userWallets) {
            thisWallet.ProcessAllWalletCurrentQuotes();
            accountAmountUsd += thisWallet.getWalletAmount();
            this.walletService.saveWallet(thisWallet);
        }
        model.addAttribute("accountAmountUsd", accountAmountUsd);
        model.addAttribute("walletList", userWallets);
        model.addAttribute("cryptoList", this.walletService.getAllCryptocurrencies());
        return "wallet_list";
    }

    /**
     * editWallet route "/editWallet/{walletId}"
     * Allows for the wallet shell to be updated
     */
    @GetMapping("/wallets/{walletId}")
    public String walletDetail(@PathVariable(value = "walletId") long walletId, Model model, HttpSession session) {
        Wallet thisWallet = this.walletService.getWalletById(walletId);
        thisWallet.ProcessAllWalletCurrentQuotes();
        this.walletService.saveWallet(thisWallet);
        model.addAttribute("wallet",  thisWallet);
        model.addAttribute("cryptoList",  this.walletService.getAllCryptocurrencies());
        model.addAttribute("pageTitle", thisWallet.getWalletName() + " Wallet");
        return "wallet_details";
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

