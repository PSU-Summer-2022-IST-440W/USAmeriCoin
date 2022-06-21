package edu.psu.ist440.USAmeriCoin.apiModel.CoinMarketCap;

import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1

public class Quote{
    @JsonProperty("USD")
    public USD usd;
}
