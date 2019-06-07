package com.example.cryptoforecast;

import android.view.textclassifier.TextClassification;
import android.content.Context;
import android.util.Log;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


/**
    OkHttpClient client = new OkHttpClient();

    TextClassification.Request request = new TextClassification.Request.Builder()
            .url("https://rest.coinapi.io/v1/trades/BITSTAMP_SPOT_BTC_USD/history?time_start=2016-01-01T00:00:00")
            .post(body)
            .addHeader("X-CoinAPI-Key", "D75D66F0-B289-482E-ACA2-243B3634642D")
            .build();

    Response response = client.newCall(request).execute();
 **/
