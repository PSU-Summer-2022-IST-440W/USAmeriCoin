package edu.psu.ist440.USAmeriCoin.controller;

import edu.psu.ist440.USAmeriCoin.model.*;
import edu.psu.ist440.USAmeriCoin.service.WalletService;
import edu.psu.ist440.USAmeriCoin.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.stream.Collectors;

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
        LocalDateTime accountDateTime = LocalDateTime.now();
        for (Wallet thisWallet : userWallets) {
            //thisWallet.ProcessAllWalletCurrentQuotes();
            accountAmountUsd += thisWallet.getWalletAmount();
            if (accountDateTime.compareTo(thisWallet.getWalletDateTime()) > 0)
                accountDateTime = thisWallet.getWalletDateTime();
            this.walletService.saveWallet(thisWallet);
        }
        System.out.println(ChronoUnit.MINUTES.between(accountDateTime,LocalDateTime.now()));
        if ((ChronoUnit.MINUTES.between(accountDateTime,LocalDateTime.now()) < 5))
            model.addAttribute("isAmountCurrent", true);
        else
            model.addAttribute("isAmountCurrent", false);

        model.addAttribute("accountAmountUsd", accountAmountUsd);
        model.addAttribute("accountDateTime", accountDateTime);
        model.addAttribute("walletList", userWallets.stream().sorted().collect(Collectors.toList()));
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
        //thisWallet.ProcessAllWalletCurrentQuotes();
        //this.walletService.saveWallet(thisWallet);
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

