package com.example.cryptoforecast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String [] coins = {"Bitcoin","Ethereum","XRP","Bitcoin Cash", "Litecoin", "EOS", "Binance Coin", "Bitcoin SV", "Tether", "Stellar"};
        ListAdapter coinsAdapter = new CoinAdapter(this, coins);
        ListView coinListView = (ListView) findViewById(R.id.coinListView);
        coinListView.setAdapter(coinsAdapter);

        coinListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String coins = String.valueOf(parent.getItemAtPosition(position));
                    }
                }
        );

    }
}
