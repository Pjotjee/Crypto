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
        DataRequest request = new DataRequest(this);
        request.getData(this);
    }

    //** fill the list with information */
    @Override
    public void gotData(ArrayList<Integer> dataMin) {
        // Sets adapter and listener for listView filled with data from HTTP request
        TextView requestTextView = (TextView) findViewById(R.id.requestTextView);
        //requestTextView.setText("800");
        requestTextView.setText(String.valueOf(dataMin));
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


