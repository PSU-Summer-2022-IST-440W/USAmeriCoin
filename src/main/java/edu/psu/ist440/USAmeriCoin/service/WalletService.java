package edu.psu.ist440.USAmeriCoin.service;

import java.util.List;
import java.util.Optional;

import edu.psu.ist440.USAmeriCoin.model.*;
import edu.psu.ist440.USAmeriCoin.repository.*;
import edu.psu.ist440.USAmeriCoin.model.*;
import edu.psu.ist440.USAmeriCoin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * WalletService Object
 * Responsible for interacting with the Repositories to retrieve and manipulate the model object and wallet interaction
 * IST 440: IST Integration and Problem Solving
 * USAmeriCoin - Team 5
 * Date Created: Jun 6, 2022
 */

@Service
public class WalletService implements IWalletService {
    @Autowired
    private IWalletRepository walletRepository;
    @Autowired
    private ICryptocurrencyRepository cryptocurrencyRepository;

    /**
     * getAllWallets
     * @param none
     * @return List<Wallet>
     * Fetches all the wallets in the database
     */
    @Override
    public List<Wallet> getAllWallets() {
        return this.walletRepository.findAll();
    }

    /**
     * getWalletById
     * @param walletId
     * @return Wallet
     * Fetches the one wallet identified by the walletId provided
     */
    @Override
    public Wallet getWalletById(long walletId) {
        Wallet wallet = null;
        Optional<Wallet> optional = walletRepository.findById(walletId);
        if (optional.isPresent()) {
            wallet = optional.get();
        }
        return wallet;
    }

    /**
     * saveWallet (overloaded)
     * @param Wallet
     * @return none
     * Saves to the database the fields collected by each step in teh wizard
     */
    @Override
    public void saveWallet(Wallet wallet) {
        this.walletRepository.save(wallet);
    }

    /**
     * getAllVehicleConditions
     * @param none
     * @return List<VehicleCondition>
     * Fetches the Vehicle Conditions from the database
     */
    public List<Cryptocurrency> getAllCryptocurrencies() {
        return this.cryptocurrencyRepository.findAll();
    }


}
