package com.example.cryptoforecast;

import android.util.Log;


import org.ejml.simple.SimpleBase;
import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;

class DataForecast extends ArrayList<Double> {

    private ArrayList<Double> dataHigh;
    private ArrayList<Double> dataLow;
    private ArrayList<Double> dataTime;
    private ArrayList<Double> dataOpen;
    private ArrayList<Double> dataClose;
    private float autoOne;
    private float autoTwo;
    private float maOne;
    private float maTwo;
    private ArrayList<Double> listAuto = new ArrayList<Double>();
    private ArrayList<Double> listAutoOne = new ArrayList<Double>();
    private ArrayList<Double> listAutoTwo = new ArrayList<Double>();
    private ArrayList<Double> listAutoThree = new ArrayList<Double>();
    private ArrayList<Double> listAutoFour = new ArrayList<Double>();
    private ArrayList<Double> listAutoFive = new ArrayList<Double>();

    private double[][] x_newMatrix;

    private ArrayList<Double> listMaOne = new ArrayList<Double>();
    private ArrayList<Double> listMaTwo = new ArrayList<Double>();
    private ArrayList<Double> listMaThree = new ArrayList<Double>();
    private ArrayList<Double> listMaFour = new ArrayList<Double>();
    private ArrayList<Double> listMaFive = new ArrayList<Double>();

    private SimpleMatrix X, XXt_inv,b,y, y_hat,e,Xn,XXt_invn, bn, y_est, X_f, X_firstRow, yForecast;
    private SimpleMatrix X_firstRowElements;
    private ArrayList<Double> UNIX;
    private int epoch;
    private ArrayList<String> date;
    private ArrayList<Double> forecast;

    public DataForecast(ArrayList<Double> dataLow, ArrayList<Double> dataHigh, ArrayList<Double> dataTime, ArrayList<Double> dataOpen, ArrayList<Double> dataClose) {
        this.dataLow = dataLow;
        this.dataHigh = dataHigh;
        this.dataTime = dataTime;
        this.dataOpen = dataOpen;
        this.dataClose = dataClose;


    }

    public ArrayList<Double> DataForecast(ArrayList<Double> dataLow, ArrayList<Double> dataHigh, ArrayList<Double> dataTime, ArrayList<Double> dataOpen, ArrayList<Double> dataClose) {

        for (int i = 5 ; i < dataLow.size() ; i++){
            listAuto.add(dataLow.get(i));
        }
        for (int i = 4 ; i < dataLow.size()-1 ; i++){
            listAutoOne.add(dataLow.get(i));
        }
        for (int i = 3 ; i < dataLow.size()-2 ; i++){
            listAutoTwo.add(dataLow.get(i));
        }
        for (int i = 2 ; i < dataLow.size()-3 ; i++){
            listAutoThree.add(dataLow.get(i));
        }
        for (int i = 1 ; i < dataLow.size()-4 ; i++){
            listAutoFour.add(dataLow.get(i));
        }
        for (int i = 0 ; i < dataLow.size()-5 ; i++){
            listAutoFive.add(dataLow.get(i));
        }


        int m = listAuto.size();
        double[][] xMatrix = new double[m][5];
        double[][] yMatrix = new double[m][1];
        for (int i = 0 ; i < m ; i++){
            xMatrix[i] = new double[]{ listAutoOne.get(i), listAutoTwo.get(i), listAutoThree.get(i), listAutoFour.get(i), listAutoFive.get(i)};
            yMatrix[i] = new double[]{listAuto.get(i)};
        }

        y = new SimpleMatrix(yMatrix);
        X = new SimpleMatrix(xMatrix);

        // (X'X)^-1
        XXt_inv = X.transpose().mult(X).invert();
        // b= (X'X)^-1 *X' y
        b = XXt_inv.mult(X.transpose().mult(y));

        // y_hat = Xb
        y_hat = X.mult(b);
        // e = y - y_hat
        e = y.minus(y_hat);
        int n = e.getNumElements();


        for (int i = 4 ; i < n-1 ; i++){
            listMaOne.add(e.get(i));
        }
        for (int i = 3 ; i < n-2 ; i++){
            listMaTwo.add(e.get(i));
        }
        for (int i = 2 ; i < n-3 ; i++){
            listMaThree.add(e.get(i));
        }
        for (int i = 1 ; i < n-4 ; i++){
            listMaFour.add(e.get(i));
        }
        for (int i = 0 ; i < n-5 ; i++){
            listMaFive.add(e.get(i));
        }

        double[][] x_newMatrix = new double[m][10];

        for (int i = 0 ; i < n-5 ; i++) {
            x_newMatrix[i] = new double[]{ listAutoOne.get(i), listAutoTwo.get(i),
                    listAutoThree.get(i), listAutoFour.get(i), listAutoFive.get(i),
                    listMaOne.get(i),listMaTwo.get(i),listMaThree.get(i),
                    listMaFour.get(i), listMaFive.get(i) };
        }

        Xn = new SimpleMatrix(x_newMatrix);

        // (X'X)^-1
        XXt_invn = Xn.transpose().mult(Xn).invert();
        // bn are the estimators of the ARMA(5,5) model
        // b= (X'X)^-1 *X' y
        bn = XXt_invn.mult(Xn.transpose().mult(y));

        Log.d("THE VALUES OF BN ARE:", String.valueOf(bn));




        // Get the first row of the X matrix and multiply it with the b vector
        X_firstRow = X.extractVector(true, 0);



        //yForecast, X_firstRowElements.extractMatrix(0,0,0,4));

        //X_firstRow = SimpleMatrix(new double[]{X_firstRow[][],  });
        for (int i= 0 ; i < 120 ; i++){

            // Calculate the forecasted value
            yForecast = b.mult(X_firstRow);
            // Add it to the forecast array
            forecast.add(yForecast.get(0,0));

            // Update the first row of the elements
            double[] yForecastArray = yForecast.getMatrix();
            double[][] X_firstRowArray = new double[1][5];
            X_firstRowArray[0]= new double[]{yForecast.get(0),X_firstRow.get(0),X_firstRow.get(1), X_firstRow.get(2),X_firstRow.get(3) };

            SimpleMatrix X_firstRow = new SimpleMatrix(X_firstRowArray);

        }
        //forecast = new SimpleMatrix(y_est);
        //return forecast;
        return dataClose;

    }

    private ArrayList<String> Time(ArrayList<Double> unix) {

        for (int i = 0; i<unix.size() ; i++) {
            int epoch = unix.get(i).intValue();
            String dat = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date(epoch * 1000));
            date.add(dat);
        }
        return date;
    }


}