package edu.psu.ist440.USAmeriCoin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Transaction Type Object
 * One-to-Many Transaction Types in the USAmeriCoin system
 * IST 440: IST Integration and Problem Solving
 * USAmeriCoin - Team 5
 * Date Created: Jun 21, 2022
 */

@Entity
@Table(name="transaction_type")
public class TransactionType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transaction_type_id")
    private long transactionTypeId;

    @Column(name="transaction_type")
    private String transactionType;

    @OneToMany(mappedBy = "transactionType")
    private Set<Transaction> transactionList = new HashSet<>();

}
