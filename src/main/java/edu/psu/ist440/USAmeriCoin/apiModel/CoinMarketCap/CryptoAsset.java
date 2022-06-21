package edu.psu.ist440.USAmeriCoin.apiModel.CoinMarketCap;

import java.util.ArrayList;
import java.util.Date;

public class CryptoAsset {
    public int id;
    public String name;
    public String symbol;
    public String slug;
    public int num_market_pairs;
    public Date date_added;
    public ArrayList<Tag> tags;
    public int max_supply;
    public double circulating_supply;
    public double total_supply;
    public int is_active;
    public Object platform;
    public int cmc_rank;
    public int is_fiat;
    public double self_reported_circulating_supply;
    public double self_reported_market_cap;
    public Object tvl_ratio;
    public Date last_updated;
    public Quote quote;
}

