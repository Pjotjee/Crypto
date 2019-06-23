package com.example.cryptoforecast;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
        if (singleCoinItem == "ETH" || singleCoinItem == "Ethereum"){
            coinImage.setImageResource(R.drawable.eth);
        }else if (singleCoinItem == "XRP" ) {
            coinImage.setImageResource(R.drawable.xrp);
        }else if (singleCoinItem == "LTC" || singleCoinItem == "Litecoin") {
            coinImage.setImageResource(R.drawable.ltc);
        }else if (singleCoinItem == "BCH" || singleCoinItem == "Bitcoin Cash") {
            coinImage.setImageResource(R.drawable.bch);
        }else if (singleCoinItem == "EOS" ) {
            coinImage.setImageResource(R.drawable.eos);
        }else if (singleCoinItem == "BNB" || singleCoinItem == "Binance Coin") {
            coinImage.setImageResource(R.drawable.bnb);
        }else if (singleCoinItem == "BSV" || singleCoinItem == "Bitcoin SV") {
            coinImage.setImageResource(R.drawable.bsv);
        }else if (singleCoinItem == "USDT" || singleCoinItem == "Tether") {
            coinImage.setImageResource(R.drawable.usdt);
        }else if (singleCoinItem == "XLM" || singleCoinItem == "Stellar") {
            coinImage.setImageResource(R.drawable.xlm);
        } else {
            coinImage.setImageResource(R.drawable.btc);
        }
        String image = "R.drawable."+ singleCoinItem;
        //coinImage.setImageResource(Integer.valueOf(image));
        return coinView;

    }
}
