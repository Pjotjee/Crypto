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

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataActivity extends AppCompatActivity {
public String coinName;
private TextView requestTextView;
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

        requestTextView = findViewById(R.id.requestTextView);

        OkHttpClient client = new OkHttpClient();
        String url = "https://rest.coinapi.io/v1/trades/BITSTAMP_SPOT_BTC_USD/history?time_start=2016-01-01T00:00:00";
        Request request = new Request.Builder()
                .url(url)
                //.post(body)
                .addHeader("X-CoinAPI-Key", "D75D66F0-B289-482E-ACA2-243B3634642D")
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


