package edu.psu.ist440.USAmeriCoin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="wallet_currency")
public class WalletCurrency implements Serializable, Comparable<WalletCurrency> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="currency_id")
    private long currencyId;
    @Column(name="quantity")
    private Double quantity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="wallet_id")
    private Wallet currencyWallet;

    @OneToMany(mappedBy = "transactionCurrency")
    private Set<Transaction> currencyTransactions = new HashSet<>();

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name="crypto_id")
    private Cryptocurrency currencyCrypto;

    public long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(long currencyId) {
        this.currencyId = currencyId;
    }

    public Wallet getCurrencyWallet() {
        return currencyWallet;
    }

    public void setCurrencyWallet(Wallet currencyWallet) {
        this.currencyWallet = currencyWallet;
    }

    public Cryptocurrency getCurrencyCrypto() {
        return currencyCrypto;
    }

    public void setCurrencyCrypto(Cryptocurrency currencyCrypto) {
        this.currencyCrypto = currencyCrypto;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Override
    public int compareTo(WalletCurrency o) {
        return (int)this.getCurrencyCrypto().getCryptoId() - (int)o.getCurrencyCrypto().getCryptoId();
    }
}
