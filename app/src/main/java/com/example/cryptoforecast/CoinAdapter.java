package com.example.cryptoforecast;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

class CoinAdapter extends ArrayAdapter<String> {

    public CoinAdapter(Context context, String[] coins) {
        super(context, R.layout.coin_detail , coins);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater coinInflater = LayoutInflater.from(getContext());
        View coinView = coinInflater.inflate(R.layout.coin_detail, parent, false);

        String singleCoinItem = getItem(position);
        TextView coinText = (TextView) coinView.findViewById(R.id.coinTextView);
        ImageView coinImage = (ImageView) coinView.findViewById(R.id.coinImageView);

        coinText.setText(singleCoinItem);
        coinImage.setImageResource(R.drawable.bitcoin);
        return coinView;

    }
}
