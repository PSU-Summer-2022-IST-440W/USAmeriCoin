package edu.psu.ist440.USAmeriCoin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="cryptocurrency")
public class Cryptocurrency implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="crypto_id")
    private long cryptoId;
    @Column(name="crypto_name")
    private String cryptoName;
    @Column(name="crypto_logo")
    private String cryptoLogo;

    @JsonIgnore
    @OneToMany(mappedBy = "currencyCrypto")
    private Set<WalletCurrency> walletCryptos = new HashSet<>();

    @Override
    public String toString() {
        return getCryptoName();
    }

    public long getCryptoId() {
        return cryptoId;
    }

    public void setCryptoId(long cryptoId) {
        this.cryptoId = cryptoId;
    }

    public String getCryptoName() {
        return cryptoName;
    }

    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }

    public String getCryptoLogo() {
        return cryptoLogo;
    }

    public void setCryptoLogo(String cryptoLogo) {
        this.cryptoLogo = cryptoLogo;
    }

    public Set<WalletCurrency> getWalletCryptos() {
        return walletCryptos;
    }

    public void setWalletCryptos(Set<WalletCurrency> walletCryptos) {
        this.walletCryptos = walletCryptos;
    }
}
