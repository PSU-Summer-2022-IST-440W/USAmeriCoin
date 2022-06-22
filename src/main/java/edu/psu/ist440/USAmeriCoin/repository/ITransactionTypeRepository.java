package edu.psu.ist440.USAmeriCoin.repository;

import edu.psu.ist440.USAmeriCoin.model.Transaction;
import edu.psu.ist440.USAmeriCoin.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * IUserRepository Repository Interface
 * Defines the required properties and methods of the repository
 * IST 440: IST Integration and Problem Solving
 * USAmeriCoin - Team 5
 * Date Created: Jun 20, 2022
 */

@Repository
public interface ITransactionTypeRepository extends JpaRepository<TransactionType,Long> {
    @Override
    Optional<TransactionType> findById(Long transactionId);
}
