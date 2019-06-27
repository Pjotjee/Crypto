package com.example.cryptoforecast;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

/*
The first activity where the user can select one of the 10 coins from the list. And will be send to
the DataActivity.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String [] symbol = {"BTC", "ETH", "XRP", "BCH", "LTC", "EOS", "BNB", "BSV", "USDT", "XLM" };
        ListAdapter coinsAdapter = new CoinAdapter(this, symbol);
        ListView coinListView = (ListView) findViewById(R.id.coinListView);
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
