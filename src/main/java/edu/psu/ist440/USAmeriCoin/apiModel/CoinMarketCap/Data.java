package edu.psu.ist440.USAmeriCoin.apiModel.CoinMarketCap;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1

import java.util.ArrayList;

public class Data{
    @JsonProperty("Asset")
    @JsonAlias({"BTC", "ETH"})
    public ArrayList<CryptoAsset> cryptoAssets;
}
