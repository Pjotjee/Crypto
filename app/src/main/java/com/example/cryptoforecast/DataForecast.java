package com.example.cryptoforecast;

import android.util.Log;
import org.ejml.simple.SimpleMatrix;
import java.util.ArrayList;

class DataForecast extends ArrayList<Double> {
    

    public static ArrayList<Double> DataForecast(ArrayList<Double> data) {


        ArrayList<Double> listAuto = new ArrayList<Double>();
        ArrayList<Double> listAutoOne = new ArrayList<Double>();
        ArrayList<Double> listAutoTwo = new ArrayList<Double>();
        ArrayList<Double> listAutoThree = new ArrayList<Double>();
        ArrayList<Double> listAutoFour = new ArrayList<Double>();
        ArrayList<Double> listAutoFive = new ArrayList<Double>();
        SimpleMatrix X, XXt_inv,b,y, y_hat,e, X_firstRow, yForecast;
        ArrayList<String> date;
        ArrayList<Double> forecast;

        for (int i = 5 ; i < data.size() ; i++){
            listAuto.add(data.get(i));
        }
        for (int i = 4 ; i < data.size()-1 ; i++){
            listAutoOne.add(data.get(i));
        }
        for (int i = 3 ; i < data.size()-2 ; i++){
            listAutoTwo.add(data.get(i));
        }
        for (int i = 2 ; i < data.size()-3 ; i++){
            listAutoThree.add(data.get(i));
        }
        for (int i = 1 ; i < data.size()-4 ; i++){
            listAutoFour.add(data.get(i));
        }
        for (int i = 0 ; i < data.size()-5 ; i++){
            listAutoFive.add(data.get(i));
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


        //double[][] x_newMatrix = new double[m][10];


        // Get the first row of the X matrix and multiply it with the b vector
        X_firstRow = X.extractVector(true, 0);


        for (int i= 0 ; i < 60 ; i++){

            // Calculate the forecasted value
            yForecast = b.mult(X_firstRow);
            // Add it to the forecast array
            data.add(yForecast.get(0,0));

            // Update the first row of the elements
            //int yForecastArray = yForecast.getMatrix();
            double[][] X_firstRowArray = new double[1][5];
            X_firstRowArray[0]= new double[]{yForecast.get(0),X_firstRow.get(0),X_firstRow.get(1), X_firstRow.get(2),X_firstRow.get(3) };

            X_firstRow = new SimpleMatrix(X_firstRowArray);

        }
        return data;
    }

    private ArrayList<String> Time(ArrayList<Double> unix) {
        ArrayList<String> date = null;
        for (int i = 0; i<unix.size() ; i++) {
            int epoch = unix.get(i).intValue();
            String dat = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date(epoch * 1000));
            date.add(dat);
        }
        return date;
    }

    
}