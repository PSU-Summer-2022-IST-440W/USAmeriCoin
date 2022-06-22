package edu.psu.ist440.USAmeriCoin.service;

import edu.psu.ist440.USAmeriCoin.model.Transaction;
import edu.psu.ist440.USAmeriCoin.model.TransactionType;
import edu.psu.ist440.USAmeriCoin.model.Wallet;

import java.util.List;

/**
 * IWalletService Interface
 * Defines the required properties and methods of the service
 * IST 440: IST Integration and Problem Solving
 * USAmeriCoin - Team 5
 * Date Created: Jun 21, 2022
 */

public interface ITransactionService {
    List<Transaction> getAllTransactions();
    List<TransactionType> getAllTransactionTypes();
    void saveTransaction(Transaction transaction);
    Transaction getTransactionById(long transactionId);
}
