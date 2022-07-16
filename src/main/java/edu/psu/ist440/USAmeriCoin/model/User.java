package edu.psu.ist440.USAmeriCoin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.stream.Collectors;

/**
 * User Object
 * Core object in the USAmeriCoin system
 * IST 440: IST Integration and Problem Solving
 * USAmeriCoin - Team 5
 * Date Created: Jun 6, 2022
 */

@Entity
@NamedQuery(name = "User.findByUsername", query = "FROM User WHERE username = ?1")
@Table(name="user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private long userId;

    @Column(name="username")
    private String username;
    @Column(name="password", updatable=false)
    private String password;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email")
    private String email;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "walletUser")
    private Set<Wallet> userWallets = new HashSet<>();

    public Set<Wallet> getUserWallets() {
        return this.userWallets;
    }

    @JsonIgnore
    public Set<Role> getRoles() {
        return this.roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) { this.username = username; }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = encodePassword(password);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return firstName + ' ' + lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String encodePassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] userPasswordHash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(userPasswordHash);
        }
        catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    public boolean isUser() {
        boolean isUser = false;
        for (Role thisRole : roles) {
            if (thisRole.getRoleName().equals("User"))
                isUser = true;
        }
        return isUser;
    }

    public boolean isAdministrator() {
        boolean isAdministrator = false;
        for (Role thisRole : roles) {
            if (thisRole.getRoleName().equals("Administrator"))
                isAdministrator = true;
        }
        return isAdministrator;
    }

    @JsonIgnore
    public String getRoleList() {
        return this.roles.stream().map(r -> r.toString()).collect(Collectors.joining(","));
    }

    @Override
    public String toString() {
        return this.firstName + ' ' + this.lastName;
    }
}
