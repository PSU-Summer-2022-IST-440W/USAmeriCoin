package edu.psu.ist440.USAmeriCoin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Wallet Object
 * Core object of every wallet shell created in the system
 * IST 440: IST Integration and Problem Solving
 * USAmeriCoin - Team 5
 * Date Created: Jun 6, 2022
 */


@Entity
@Table(name="transaction")
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transaction_id")
    private Long transactionId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="currency_id")
    private WalletCurrency transactionCurrency;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="transaction_type_id")
    private TransactionType transactionType;

    @Column(name="quantity")
    private Double quantity;
    @Column(name="amount_usd")
    private Double amountUsd;

    @Column(name = "transaction_dt", columnDefinition = "DATETIME") //, updatable=false, insertable = false)
    private LocalDateTime transactionDateTime;

}