package edu.psu.ist440.USAmeriCoin.repository;

import edu.psu.ist440.USAmeriCoin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * IUserRepository Repository Interface
 * Defines the required properties and methods of the repository
 * IST 440: IST Integration and Problem Solving
 * USAmeriCoin - Team 5
 * Date Created: Jun 6, 2022
 */

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    @Override
    Optional<User> findById(Long userId);
    Optional<User> findByUsername(String username);
}
