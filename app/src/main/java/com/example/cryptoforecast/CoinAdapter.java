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
        if (singleCoinItem == "ETH"){
            coinImage.setImageResource(R.drawable.eth);
        }else if (singleCoinItem == "XRP") {
            coinImage.setImageResource(R.drawable.xrp);
        }else if (singleCoinItem == "LTC") {
            coinImage.setImageResource(R.drawable.ltc);
        }else if (singleCoinItem == "BCH") {
            coinImage.setImageResource(R.drawable.bch);
        }else if (singleCoinItem == "EOS") {
            coinImage.setImageResource(R.drawable.eos);
        }else if (singleCoinItem == "BNB") {
            coinImage.setImageResource(R.drawable.bnb);
        }else if (singleCoinItem == "BSV") {
            coinImage.setImageResource(R.drawable.bsv);
        }else if (singleCoinItem == "USDT") {
            coinImage.setImageResource(R.drawable.usdt);
        }else if (singleCoinItem == "XLM") {
            coinImage.setImageResource(R.drawable.xlm);
        } else {
            coinImage.setImageResource(R.drawable.btc);
        }
        String image = "R.drawable."+ singleCoinItem;
        //coinImage.setImageResource(Integer.valueOf(image));
        return coinView;

    }
}
