package edu.psu.ist440.USAmeriCoin.controller;

import edu.psu.ist440.USAmeriCoin.model.Cryptocurrency;
import edu.psu.ist440.USAmeriCoin.model.Transaction;
import edu.psu.ist440.USAmeriCoin.model.Wallet;
import edu.psu.ist440.USAmeriCoin.model.WalletCurrency;
import edu.psu.ist440.USAmeriCoin.service.TransactionService;
import edu.psu.ist440.USAmeriCoin.service.UserService;
import edu.psu.ist440.USAmeriCoin.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * TransactionController
 * Manages all the user transactions between wallets of the system
 * IST 440: IST Integration and Problem Solving
 * USAmeriCoin - Team 5
 * Date Created: Jun 15, 2022
 */

@Controller
public class TransactionController {
    @Autowired
    private UserService userService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private TransactionService transactionService;

    /**
     * wallets route "/trade"
     * Allows for the wallet shell to be updated
     */
    @GetMapping("/trade")
    public String newTrade(Model model, HttpSession session) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("pageTitle", "Trade Assets");
        Set<Wallet> userWallets = this.userService.getUserById((long)session.getAttribute("authUserId")).getUserWallets();
        model.addAttribute("tradeStep", 1);
        model.addAttribute("walletList", userWallets);
        model.addAttribute("wallet", new Wallet());
        return "trade";
    }

    /**
     * walletStatus route "/trade/step2"
     * The POST-processing of the above trade updates
     */
    @PostMapping(value="/trade/step2")
    public String executeTrade(@ModelAttribute("walletId") int walletId, @ModelAttribute("transaction") Transaction thisTransaction, Model model) {
        Wallet thisWallet = this.walletService.getWalletById(walletId);
        model.addAttribute("transaction", thisTransaction);
        model.addAttribute("tradeStep", 2);
        model.addAttribute("pageTitle", "Trade Assets - Step 2");
        model.addAttribute("wallet", thisWallet);
        model.addAttribute("walletCurrency", new WalletCurrency());
        model.addAttribute("walletCurrencyList", thisWallet.getWalletCurrencies());
        return "trade";
    }

}

