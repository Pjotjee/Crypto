package com.example.cryptoforecast;

import android.view.textclassifier.TextClassification;

public class DataRequest {




    OkHttpClient client = new OkHttpClient();

    TextClassification.Request request = new TextClassification.Request.Builder()
            .url("https://rest.coinapi.io/v1/trades/BITSTAMP_SPOT_BTC_USD/history?time_start=2016-01-01T00:00:00")
            .post(body)
            .addHeader("X-CoinAPI-Key", "73034021-0EBC-493D-8A00-E0F138111F41s")
            .build();

    Response response = client.newCall(request).execute();
}
