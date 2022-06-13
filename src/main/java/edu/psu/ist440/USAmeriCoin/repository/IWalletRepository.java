package edu.psu.ist440.USAmeriCoin.repository;

import edu.psu.ist440.USAmeriCoin.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * IWalletRepository Repository Interface
 * Defines the required properties and methods of the repository
 * IST 440: IST Integration and Problem Solving
 * USAmeriCoin - Team 5
 * Date Created: Jun 6, 2022
 */


@Repository
public interface IWalletRepository extends JpaRepository<Wallet,Long> {
    @Override
    Optional<Wallet> findById(Long walletId);
}

