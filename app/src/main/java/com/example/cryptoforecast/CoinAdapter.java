package com.example.cryptoforecast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/*
The coin adapter class is sets the right text and images into the list of MainActivity.
 */
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
        TextView coinName = (TextView) coinView.findViewById(R.id.coinName);
        TextView coinPrice = (TextView) coinView.findViewById(R.id.coinPrice);
        ImageView coinImage = (ImageView) coinView.findViewById(R.id.coinImageView);
        coinText.setText(singleCoinItem);
        if (singleCoinItem == "ETH" || singleCoinItem == "Ethereum"){
            coinImage.setImageResource(R.drawable.eth);
            coinName.setText("Ethereum");
            coinPrice.setText("$309,53");
        }else if (singleCoinItem == "XRP" ) {
            coinImage.setImageResource(R.drawable.xrp);
            coinName.setText("XRP");
            coinPrice.setText("$0,463333");
        }else if (singleCoinItem == "LTC" || singleCoinItem == "Litecoin") {
            coinImage.setImageResource(R.drawable.ltc);
            coinName.setText("Litecoin");
            coinPrice.setText("$135,67");
        }else if (singleCoinItem == "BCH" || singleCoinItem == "Bitcoin Cash") {
            coinImage.setImageResource(R.drawable.bch);
            coinName.setText("Bitcoin Cash");
            coinPrice.setText("$472,98");
        }else if (singleCoinItem == "EOS" ) {
            coinImage.setImageResource(R.drawable.eos);
            coinName.setText("EOS");
            coinPrice.setText("$7,21");
        }else if (singleCoinItem == "BNB" || singleCoinItem == "Binance Coin") {
            coinImage.setImageResource(R.drawable.bnb);
            coinName.setText("Binance Coin");
            coinPrice.setText("$37,33");
        }else if (singleCoinItem == "BSV" || singleCoinItem == "Bitcoin SV") {
            coinImage.setImageResource(R.drawable.bsv);
            coinName.setText("Bitcoin SV");
            coinPrice.setText("$235,39");
        }else if (singleCoinItem == "USDT" || singleCoinItem == "Tether") {
            coinImage.setImageResource(R.drawable.usdt);
            coinName.setText("Tether");
            coinPrice.setText("$0,997294");
        }else if (singleCoinItem == "XLM" || singleCoinItem == "Stellar") {
            coinImage.setImageResource(R.drawable.xlm);
            coinName.setText("Stellar");
            coinPrice.setText("$0,127382");
        } else {
            coinImage.setImageResource(R.drawable.btc);
            coinName.setText("Bitcoin");
            coinPrice.setText("$11.023,75");
        }
        return coinView;
    }
}
