package com.example.cryptoforecast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextClassification;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataActivity extends AppCompatActivity implements DataRequest.Callback{
public String coinName;
private TextView requestTextView;
private int data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forecast_data);

        // receive the coin name through the intent
        Bundle coinSelected = getIntent().getExtras();
        if (coinSelected == null){
            return;
        }
        // Find the name textfield and set the right name
        String coinName = coinSelected.getString("coinName");
        TextView coinText = (TextView) findViewById(R.id.coinNameTextView) ;
        coinText.setText(coinName);

        //Gets categories with HTTP request
        DataRequest request = new DataRequest(this);
        request.getData(this);

        /** requestTextView = findViewById(R.id.requestTextView);

        OkHttpClient client = new OkHttpClient();
        //String url = "https://rest.coinapi.io/v1/trades/BITSTAMP_SPOT_BTC_USD/history?time_start=2016-01-01T00:00:00";
        String url = "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=USD,JPY,EUR";
        Request request = new Request.Builder()
                .url(url)
                //.post(body)
                //.addHeader("Apikey", "D75D66F0-B289-482E-ACA2-243B3634642D")
                .addHeader("Apikey", "635b4471780f89cd7d72b4afbfc10cde1157354272a6583e849964c523a7b75b")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    DataActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            requestTextView.setText(myResponse);
                        }
                    });

                }
            }
        });
        */

    }
    //** fill the list with information */
    @Override
    public void gotData(ArrayList<Integer> dataMin) {
        // Sets adapter and listener for listView filled with data from HTTP request
        ArrayAdapter<String> dataAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        TextView requestTextView = (TextView) findViewById(R.id.requestTextView);
        requestTextView.setText(data.get(0));
        /**ListView menuList = findViewById(R.id.dataList);
        menuList.setAdapter(dataAdapter);
        menuList.setOnItemClickListener(new OnItemItemClick()); */
    }

    //** check for errors */
    @Override
    public void gotDataError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void onClick(View view){

        // receive the coin name through the intent
        Bundle coinSelected = getIntent().getExtras();
        if (coinSelected == null){
            return;
        }
        // Find the name textfield and set the right name
        String coinName = coinSelected.getString("coinName");

        Intent newsIntent = new Intent(DataActivity.this, NewsActivity.class);

        newsIntent.putExtra("coinName", coinName);
        startActivity(newsIntent);
    }
}


