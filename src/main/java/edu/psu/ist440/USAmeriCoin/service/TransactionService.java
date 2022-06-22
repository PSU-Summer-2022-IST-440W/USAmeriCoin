package edu.psu.ist440.USAmeriCoin.service;

import edu.psu.ist440.USAmeriCoin.model.*;
import edu.psu.ist440.USAmeriCoin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * TransactionService Object
 * Responsible for interacting with the Repositories to retrieve and manipulate the model object and user interaction
 * IST 440: IST Integration and Problem Solving
 * USAmeriCoin - Team 5
 * Date Created: Jun 6, 2022
 */


@Service
public class TransactionService implements ITransactionService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IWalletRepository walletRepository;
    @Autowired
    private ITransactionRepository transactionRepository;
    @Autowired
    private ITransactionTypeRepository transactionTypeRepository;

    @Override
    public List<TransactionType> getAllTransactionTypes() {
        return this.transactionTypeRepository.findAll();
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return null;
    }

    @Override
    public void saveTransaction(Transaction transaction) {

    }

    @Override
    public Transaction getTransactionById(long transactionId) {
        return null;
    }
}