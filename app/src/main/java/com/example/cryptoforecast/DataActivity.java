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

        //chart = findViewById(R.id.chart);
        //chart.setBackgroundColor(Color.WHITE);
    }

    //** fill the list with information */
    @Override
    public void gotData(ArrayList<Double> dataLow, ArrayList<Double> dataHigh, ArrayList<Double> dataTime, ArrayList<Double> dataOpen, ArrayList<Double> dataClose) {

        // First we make the forcast with the data
        ArrayList<Double> dataLowF = DataForecast.DataForecast(dataLow);
        ArrayList<Double> dataHighF = DataForecast.DataForecast(dataHigh);
        ArrayList<Double> dataCloseF = DataForecast.DataForecast(dataClose);
        ArrayList<Double> dataOpenF = DataForecast.DataForecast(dataOpen);


        for (int i=0; i<120;i++){
            int last = dataTime.size();
            dataTime.add(dataTime.get(last-1)+60);
        }

        candleChart = (CandleStickChart) findViewById(R.id.chart);
        candleChart.setHighlightPerDragEnabled(true);
        candleChart.setBackgroundColor(Color.BLACK);
        int tijd =0;
        ArrayList<CandleEntry> dataCandle= new ArrayList<>();
        //for (int i = 90 ; i < 150 ; i++ ) {
        for (int i = 0 ; i < 60 ; i++ ) {
            tijd =i;
            LOW = dataLowF.get(i).floatValue();
            HIGH = dataHighF.get(i).floatValue() ;
            TIME = dataTime.get(i).floatValue();
            CLOSE = dataCloseF.get(i).floatValue();
            OPEN = dataOpenF.get(i).floatValue();
            dataCandle.add(new CandleEntry(tijd, HIGH , LOW, OPEN, CLOSE));
        }

        CandleDataSet set1 = new CandleDataSet(dataCandle, "Data");
        set1.setColor(Color.rgb(80, 80, 80));
        set1.setShadowColor(Color.DKGRAY);
        set1.setShadowWidth(0.7f);
        set1.setDecreasingColor(Color.RED);
        set1.setDecreasingPaintStyle(Paint.Style.FILL);
        set1.setIncreasingColor(Color.rgb(122, 242, 84));
        set1.setIncreasingPaintStyle(Paint.Style.STROKE);
        set1.setNeutralColor(Color.GRAY);
        //set1.setValueTextColor(Color.RED);
        set1.setDrawValues(false);

        CandleData data1 = new CandleData(set1);

        candleChart.setData(data1);
        candleChart.invalidate();

        int index = 59; //dataLow.size();
        String currentLow = String.valueOf(dataLow.get(index));
        TextView coinLow = (TextView) findViewById(R.id.textViewCurLow) ;
        coinLow.setText(currentLow);
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


