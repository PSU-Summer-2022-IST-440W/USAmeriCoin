package edu.psu.ist440.USAmeriCoin.repository;

import edu.psu.ist440.USAmeriCoin.model.Cryptocurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * IVehicleConditionRepository Interface
 * Defines the required properties and methods of the repository
 * IST 440: IST Integration and Problem Solving
 * USAmeriCoin - Team 5
 * Date Created: Jun 6, 2022
 */

@Repository
public interface ICryptocurrencyRepository extends JpaRepository<Cryptocurrency,Long> {
    @Override
    Optional<Cryptocurrency> findById(Long cryptoId);
}

