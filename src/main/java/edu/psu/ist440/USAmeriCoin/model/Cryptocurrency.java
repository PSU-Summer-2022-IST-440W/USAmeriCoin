package edu.psu.ist440.USAmeriCoin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.psu.ist440.USAmeriCoin.apiModel.CoinGecko.CoinGecko;
import edu.psu.ist440.USAmeriCoin.apiModel.CoinMarketCap.*;
import edu.psu.ist440.USAmeriCoin.service.RestApiService;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
    @Column(name="symbol")
    private String symbol;
    @Column(name="symbol_coinmarketcap")
    private String symbolCoinMarketCap;
    @Column(name="symbol_coingecko")
    private String symbolCoinGecko;
    @Column(name="amount_usd")
    private Double amountUsd;
    @Column(name="amount_dt")
    private LocalDateTime amountDateTime;

    @Transient
    private CoinMarketCap coinMarketCap;
    @Transient
    private CoinGecko coinGecko;

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

    private boolean fetchCryptoApiData() {
        CoinMarketCap thisCMP = new CoinMarketCap();
        CoinGecko thisCG = new CoinGecko();
        boolean successfulDataFetch = false;
        if (!this.symbol.equals("USAC")) {
            if (ChronoUnit.MINUTES.between(this.amountDateTime,LocalDateTime.now()) > 10) {
                RestApiService restService = new RestApiService();
                thisCMP = restService.CoinMarketCapApi("https://pro-api.coinmarketcap.com/v2/cryptocurrency/quotes/latest?symbol=" + this.getSymbolCoinMarketCap());
                if (thisCMP != null) {
                    successfulDataFetch = true;
                    this.coinMarketCap = thisCMP;
                    this.amountUsd = thisCMP.data.cryptoAssets.get(0).quote.usd.price;
                    this.amountDateTime = LocalDateTime.now();
                } else {
                    thisCG = restService.CoinGeckoApi("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&ids=" + this.symbolCoinGecko);
                    if (thisCG != null) {
                        successfulDataFetch = true;
                        this.coinGecko = thisCG;
                        this.amountUsd = thisCG.current_price;
                        this.amountDateTime = LocalDateTime.now();
                    }
                }
            }
        } else {
            thisCMP.data = new Data();
            thisCMP.data.cryptoAssets = new ArrayList<>();
            thisCMP.data.cryptoAssets.add(new CryptoAsset());
            thisCMP.data.cryptoAssets.get(0).quote = new Quote();
            thisCMP.data.cryptoAssets.get(0).quote.usd = new USD();
            thisCMP.data.cryptoAssets.get(0).quote.usd.price = 1.00;
            this.amountUsd = thisCMP.data.cryptoAssets.get(0).quote.usd.price;
            this.amountDateTime = LocalDateTime.now();
        }
        return successfulDataFetch;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getAmountUsd() {
        fetchCryptoApiData();
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


    public String getSymbolCoinGecko() {
        return symbolCoinGecko;
    }

    public void setSymbolCoinGecko(String symbolCoinGecko) {
        this.symbolCoinGecko = symbolCoinGecko;
    }

    public String getSymbolCoinMarketCap() {
        return symbolCoinMarketCap;
    }

    public void setSymbolCoinMarketCap(String symbolCoinMarketCap) {
        this.symbolCoinMarketCap = symbolCoinMarketCap;
    }
}
