package com.example.cryptoforecast;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import java.util.ArrayList;

/*
DataActivity shows a graph with the coin values and also displays some other values of the coin.
 */

public class DataActivity extends AppCompatActivity implements DataRequest.Callback{

private float LOW;
private float minLOW;
private float HIGH;
private float maxHIGH;
private int TIME;
private float CLOSE;
private float OPEN;
private CandleStickChart candleChart;

/* Create the activity. */
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forecast_data);
        // receive the coin name through the intent
        Bundle coinSelected = getIntent().getExtras();
        if (coinSelected == null){
            return;
        }
        // find the name textfield and set the right name
        String coinName = coinSelected.getString("coinName");
        TextView coinText = (TextView) findViewById(R.id.coinTextView) ;
        coinText.setText(coinName);
        DataRequest request = new DataRequest(this);
        request.getData(this, coinName);
    }

    /* Receive the values from the API and make the forecasts, then display them. */
    @Override
    public void gotData(ArrayList<Double> dataLow, ArrayList<Double> dataHigh, ArrayList<Double> dataTime, ArrayList<Double> dataOpen, ArrayList<Double> dataClose) {
        // First we make the forcast with the data
        ArrayList<Double> dataLowF = DataForecast.DataForecast(dataLow);
        ArrayList<Double> dataHighF = DataForecast.DataForecast(dataHigh);
        ArrayList<Double> dataCloseF = DataForecast.DataForecast(dataClose);
        ArrayList<Double> dataOpenF = DataForecast.DataForecast(dataOpen);
        ArrayList<CandleEntry> dataCandle= new ArrayList<>();
        for (int i=0; i<120;i++){
            int last = dataTime.size();
            dataTime.add(dataTime.get(last-1)+60);
        }
        candleChart = (CandleStickChart) findViewById(R.id.chart);
        candleChart.setHighlightPerDragEnabled(true);
        candleChart.setBackgroundColor(Color.DKGRAY);
        TIME =0;
        LOW = 0;
        minLOW = 999999;
        HIGH = 0;
        maxHIGH = 0;
        // the past values
        for (int i = 0 ; i < 30 ; i++ ) {
            TIME =i-30;
            LOW = dataLowF.get(i).floatValue();
            if (minLOW > LOW){
                minLOW = LOW;
            }
            HIGH = dataHighF.get(i).floatValue() ;
            if (maxHIGH < HIGH){
                maxHIGH = HIGH;
            }
            CLOSE = dataCloseF.get(i).floatValue();
            OPEN = dataOpenF.get(i).floatValue();
            dataCandle.add(new CandleEntry(TIME, HIGH , LOW, OPEN, CLOSE));
        }
        TextView minLowPast = findViewById(R.id.textViewMinPrice);
        minLowPast.setText(String.valueOf(minLOW));
        TextView maxHighPast = findViewById(R.id.textViewMaxPrice);
        maxHighPast.setText(String.valueOf(maxHIGH));
        LOW = 0;
        minLOW = 999999;
        HIGH = 0;
        maxHIGH = 0;
        // the forecast values
        for (int i = 30 ; i < 60 ; i++ ) {
            TIME =i-30;
            LOW = dataLowF.get(i).floatValue();
            if (minLOW > LOW){
                minLOW = LOW;
            }
            HIGH = dataHighF.get(i).floatValue() ;
            if (maxHIGH < HIGH){
                maxHIGH = HIGH;
            }
            CLOSE = dataCloseF.get(i).floatValue();
            OPEN = dataOpenF.get(i).floatValue();
            dataCandle.add(new CandleEntry(TIME, HIGH , LOW, OPEN, CLOSE));
        }
        TextView minLowFuture = findViewById(R.id.textViewMinPriceFuture);
        minLowFuture.setText(String.valueOf(minLOW));
        TextView maxHighFuture = findViewById(R.id.textViewMaxPriceFuture);
        maxHighFuture.setText(String.valueOf(maxHIGH));
        // set the chart values
        CandleDataSet set1 = new CandleDataSet(dataCandle, "Data");
        set1.setColor(Color.rgb(80, 80, 80));
        set1.setShadowColor(Color.DKGRAY);
        set1.setShadowWidth(0.7f);
        set1.setDecreasingColor(Color.RED);
        set1.setDecreasingPaintStyle(Paint.Style.FILL);
        set1.setIncreasingColor(Color.rgb(122, 242, 84));
        set1.setIncreasingPaintStyle(Paint.Style.STROKE);
        set1.setNeutralColor(Color.GRAY);
        set1.setValueTextColor(Color.WHITE);
        set1.setDrawValues(false);
        CandleData data1 = new CandleData(set1);
        candleChart.setData(data1);
        candleChart.invalidate();
        // set the other values
        int index = 29; //dataLow.size();
        String currentLow = String.valueOf(dataLow.get(index));
        TextView coinLow = (TextView) findViewById(R.id.textViewCurLow) ;
        coinLow.setText(currentLow);
        String currentHigh = String.valueOf(dataHigh.get(index));
        TextView coinHigh = (TextView) findViewById(R.id.textViewCurHigh) ;
        coinHigh.setText(currentHigh);
    }

    /* Check for errors in the request. */
    @Override
    public void gotDataError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /* OnClick method to go to the news activity of the coin. */
    public void onClick(View view){
        // receive the coin name through the intent
        Bundle coinSelected = getIntent().getExtras();
        if (coinSelected == null){
            return;
        }
        // find the name textfield and set the right name
        String coinName = coinSelected.getString("coinName");
        Intent newsIntent = new Intent(DataActivity.this, NewsActivity.class);
        newsIntent.putExtra("coinName", coinName);
        startActivity(newsIntent);
    }
}