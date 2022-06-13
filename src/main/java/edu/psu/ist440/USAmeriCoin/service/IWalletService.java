package edu.psu.ist440.USAmeriCoin.service;

import edu.psu.ist440.USAmeriCoin.model.Wallet;

import java.util.List;

/**
 * IWalletService Interface
 * Defines the required properties and methods of the service
 * IST 440: IST Integration and Problem Solving
 * USAmeriCoin - Team 5
 * Date Created: Jun 6, 2022
 */

public interface IWalletService {
    List<Wallet> getAllWallets();
    void saveWallet(Wallet wallet);
    Wallet getWalletById(long walletId);

}
