package com.example.cryptoforecast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String [] coins = {"Bitcoin","Ethereum","XRP","Bitcoin Cash", "Litecoin", "EOS", "Binance Coin", "Bitcoin SV", "Tether", "Stellar"};
        String [] symbol = {"BTC", "ETH", "XRP", "BCH", "LTC", "EOS", "BNB", "BSV", "USDT", "XLM" };
        ListAdapter coinsAdapter = new CoinAdapter(this, symbol);
        ListView coinListView = (ListView) findViewById(R.id.coinListView);
        //ImageView coinImageView = (ImageView) findViewById(R.id.coinImageView);
        coinListView.setAdapter(coinsAdapter);

        coinListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String coinName = String.valueOf(parent.getItemAtPosition(position));

                        // send the menuItem that was clicked to the activity through the intent
                        Intent intent = new Intent(MainActivity.this, DataActivity.class);
                        intent.putExtra("coinName", coinName);
                        startActivity(intent);
                    }
                }
        );

    }
}
