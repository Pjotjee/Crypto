package com.example.cryptoforecast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DataActivity extends AppCompatActivity {
public String coinName;
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


