package edu.psu.ist440.USAmeriCoin.service;

import edu.psu.ist440.USAmeriCoin.model.User;
import edu.psu.ist440.USAmeriCoin.model.Password;
import edu.psu.ist440.USAmeriCoin.model.Role;
import java.util.List;

/**
 * IUserService Interface
 * Defines the required properties and methods of the service
 * IST 440: IST Integration and Problem Solving
 * USAmeriCoin - Team 5
 * Date Created: Jun 6, 2022
 */


public interface IUserService {
    List<User> getAllUsers();
    List<Role> getAllRoles();
    void saveUser(User user);
    void savePassword(Password pass);
    User getUserById(long userId);
    Password getPasswordById(long userId);
    User getUserByUsername(String username);
}
