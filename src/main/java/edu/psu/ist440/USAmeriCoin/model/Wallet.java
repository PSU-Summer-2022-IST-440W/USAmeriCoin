package edu.psu.ist440.USAmeriCoin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Wallet Object
 * Core object of every wallet shell created in the system
 * IST 440: IST Integration and Problem Solving
 * USAmeriCoin - Team 5
 * Date Created: Jun 6, 2022
 */


@Entity
@Table(name="wallet")
public class Wallet implements Serializable, Comparable<Wallet> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="wallet_id")
    private Long walletId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id")
    private User walletUser;

    @OneToMany(mappedBy = "currencyWallet")
    private Set<WalletCurrency> walletCurrencies = new HashSet<>();

    @Column(name="name")
    private String walletName;
    //@Column(name="amount_usd")
    //private Double walletAmount;
    @Column(name="address")
    private String walletAddress;
    @Column(name="public_key")
    private String publicKey;
    @Column(name="private_key")
    private String privateKey;
//    @Column(name = "amount_dt", columnDefinition = "DATETIME") //, updatable=false, insertable = false)
//    private LocalDateTime amountDateTime;

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

/*    public Double getWalletAmount() {
        return walletAmount;
    }

    public void setWalletAmount(Double walletAmount) {
        this.walletAmount = walletAmount;
    }
*/
    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public User getWalletUser() {
        return walletUser;
    }

    public void setWalletUser(User walletUser) {
        this.walletUser = walletUser;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public LocalDateTime getWalletDateTime() {
        LocalDateTime walletDateTime = LocalDateTime.now();
        for (WalletCurrency thisWC : this.walletCurrencies) {
            if (walletDateTime.compareTo(thisWC.getCurrencyCrypto().getAmountDateTime()) > 0)
                walletDateTime = thisWC.getCurrencyCrypto().getAmountDateTime();
        }
        return walletDateTime;
    }

    public Set<WalletCurrency> getWalletCurrencies() {
        return walletCurrencies;
    }

    public List<WalletCurrency> getSortedWalletCurrencies() {
        return walletCurrencies.stream().sorted().collect(Collectors.toList());
    }

    public void setWalletCurrencies(Set<WalletCurrency> walletCurrencies) {
        this.walletCurrencies = walletCurrencies;
    }

    public Double getWalletAmount() {
        Double walletValue = 0.0;
        for (WalletCurrency thisWC : this.walletCurrencies) {
            walletValue += thisWC.getQuantity() * thisWC.getCurrencyCrypto().getAmountUsd();
        }
        return walletValue;
    }

    @Override
    public int compareTo(Wallet o) {
        return this.getWalletName().compareTo(o.getWalletName());
    }
}
