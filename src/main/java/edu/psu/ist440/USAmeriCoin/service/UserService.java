package edu.psu.ist440.USAmeriCoin.service;

import java.util.List;
import java.util.Optional;

import edu.psu.ist440.USAmeriCoin.model.Role;
import edu.psu.ist440.USAmeriCoin.model.User;
import edu.psu.ist440.USAmeriCoin.repository.IPasswordRepository;
import edu.psu.ist440.USAmeriCoin.repository.IRoleRepository;
import edu.psu.ist440.USAmeriCoin.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.psu.ist440.USAmeriCoin.repository.*;
import edu.psu.ist440.USAmeriCoin.model.Password;

/**
 * UserService Object
 * Responsible for interacting with the Repositories to retrieve and manipulate the model object and user interaction
 * IST 440: IST Integration and Problem Solving
 * USAmeriCoin - Team 5
 * Date Created: Jun 6, 2022
 */


@Service
public class UserService implements IUserService {
    @Autowired
    private IPasswordRepository passwordRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;

    /**
     * getAllUsers
     * @param none
     * @return List<User>
     * Fetches all the users in the database
     */
    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    /**
     * getAllRoles
     * @param none
     * @return List<Role>
     * Fetches all the roles in the database
     */
    @Override
    public List<Role> getAllRoles() {
        return this.roleRepository.findAll();
    }

    /**
     * getUserById
     * @param userId
     * @return User
     * Fetches the single user identified by the userId provided
     */
    @Override
    public User getUserById(long userId) {
        User user = null;
        Optional<User> optional = userRepository.findById(userId);
        if (optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

    /**
     * getPasswordById
     * @param userId
     * @return Password
     * Fetches the hashed Password value for the userId provided
     */
    @Override
    public Password getPasswordById(long userId) {
        Optional<Password> optional = passwordRepository.findById(userId);
        return optional.get();
    }

    /**
     * getUserByUsername
     * @param username
     * @return User
     * Fetches the User object for the username string provided
     */
    public User getUserByUsername(String username) {
        User user = null;
        Optional<User> optional = userRepository.findByUsername(username);
        if (optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

    /**
     * saveUser
     * @param User
     * @return none
     * Updates the User object in the database
     */
    @Override
    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    /**
     * savePassword
     * @param Password
     * @return none
     * Updates the User's password in the database
     */
    @Override
    public void savePassword(Password pass) {
        this.passwordRepository.save(pass);
    }
}