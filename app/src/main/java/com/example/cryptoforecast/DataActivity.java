package com.example.cryptoforecast;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;

import java.util.ArrayList;


public class DataActivity extends AppCompatActivity implements DataRequest.Callback{

private CandleStickChart chart;
private float LOW;
private float HIGH;
private float TIME;
private float CLOSE;
private float OPEN;
private CandleStickChart candleChart;

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

        chart = findViewById(R.id.chart);
        chart.setBackgroundColor(Color.WHITE);
    }

    //** fill the list with information */
    @Override
    public void gotData(ArrayList<Double> dataLow, ArrayList<Double> dataHigh, ArrayList<Double> dataTime, ArrayList<Double> dataOpen, ArrayList<Double> dataClose) {




        candleChart = (CandleStickChart) findViewById(R.id.chart);

        ArrayList<CandleEntry> dataCandle= new ArrayList<>();
        for (int i = 0 ; i < dataLow.size() ; i++ ) {
            LOW = dataLow.get(i).floatValue();
            HIGH = dataHigh.get(i).floatValue() ;
            TIME = dataTime.get(i).floatValue();
            CLOSE = dataClose.get(i).floatValue();
            OPEN = dataOpen.get(i).floatValue();
            dataCandle.add(new CandleEntry(TIME, HIGH , LOW, OPEN, CLOSE));

        }

        DataForecast forecast = new DataForecast(dataLow, dataHigh, dataTime, dataOpen, dataClose);


        CandleDataSet set1 = new CandleDataSet(dataCandle, "Data");
        set1.setColor(Color.rgb(80, 80, 80));
        set1.setShadowColor(Color.DKGRAY);
        set1.setShadowWidth(0.7f);
        set1.setDecreasingColor(Color.RED);
        set1.setDecreasingPaintStyle(Paint.Style.FILL);
        set1.setIncreasingColor(Color.rgb(122, 242, 84));
        set1.setIncreasingPaintStyle(Paint.Style.STROKE);
        set1.setNeutralColor(Color.BLUE);
        set1.setValueTextColor(Color.RED);


        CandleData data1 = new CandleData(set1);

        candleChart.setData(data1);
        candleChart.invalidate();
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


