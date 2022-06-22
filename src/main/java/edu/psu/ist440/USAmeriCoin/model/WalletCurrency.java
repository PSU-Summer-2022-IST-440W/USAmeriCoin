package edu.psu.ist440.USAmeriCoin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="wallet_currency")
public class WalletCurrency implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="currency_id")
    private long currencyId;

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

    @Column(name="quantity")
    private Double quantity;
    @Column(name="amount_usd")
    private Double amountUsd;
    @Column(name = "amount_dt", columnDefinition = "DATETIME") //, updatable=false, insertable = false)
    private LocalDateTime amountDateTime;

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

    public Double getAmountUsd() {
        return amountUsd;
    }

    public void setAmountUsd(Double amountUsd) {
        this.amountUsd = amountUsd;
    }

    public LocalDateTime getAmountDateTime() {
        return amountDateTime;
    }

    public void setAmountDateTime(LocalDateTime amountDateTime) {
        this.amountDateTime = amountDateTime;
    }
}
