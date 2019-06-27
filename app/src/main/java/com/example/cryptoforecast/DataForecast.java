package com.example.cryptoforecast;


import org.ejml.simple.SimpleMatrix;
import java.util.ArrayList;

/*
Create a model based on past values, and use it to make a forecast.
 */
class DataForecast extends ArrayList<Double> {

    public static ArrayList<Double> DataForecast(ArrayList<Double> data) {
        ArrayList<Double> listAuto = new ArrayList<Double>();
        ArrayList<Double> listAutoOne = new ArrayList<Double>();
        ArrayList<Double> listAutoTwo = new ArrayList<Double>();
        ArrayList<Double> listAutoThree = new ArrayList<Double>();
        ArrayList<Double> listAutoFour = new ArrayList<Double>();
        ArrayList<Double> listAutoFive = new ArrayList<Double>();
        SimpleMatrix X, xXtInv, b, y, xFirstRow, yForecast;
        // create the auto regressive values
        for (int i = 5 ; i < data.size() ; i++){
            listAuto.add(data.get(i));
            listAutoOne.add(data.get(i-1));
            listAutoTwo.add(data.get(i-2));
            listAutoThree.add(data.get(i-3));
            listAutoFour.add(data.get(i-4));
            listAutoFive.add(data.get(i-5));
        }
        // make a matrix to do the calculations
        int m = listAuto.size();
        double[][] xMatrix = new double[m][5];
        double[][] yMatrix = new double[m][1];
        for (int i = 0 ; i < m ; i++){
            xMatrix[i] = new double[]{ listAutoOne.get(i), listAutoTwo.get(i), listAutoThree.get(i), listAutoFour.get(i), listAutoFive.get(i)};
            yMatrix[i] = new double[]{listAuto.get(i)};
        }
        // use the simpleMatrix library for the matrix calculations
        y = new SimpleMatrix(yMatrix);
        X = new SimpleMatrix(xMatrix);
        // (X'X)^-1
        xXtInv = X.transpose().mult(X).invert();
        // b= (X'X)^-1 *X' y
        b = xXtInv.mult(X.transpose().mult(y));
        // get the first row of the X matrix and multiply it with the b vector
        xFirstRow = X.extractVector(true, 0);
        for (int i= 0 ; i < 60 ; i++){
            // calculate the forecasted value
            yForecast = b.mult(xFirstRow);
            // add it to the forecast array
            data.add(yForecast.get(0,0));
            // update the first row of the elements
            double[][] X_firstRowArray = new double[1][5];
            X_firstRowArray[0]= new double[]{yForecast.get(0),xFirstRow.get(0),xFirstRow.get(1), xFirstRow.get(2),xFirstRow.get(3) };
            xFirstRow = new SimpleMatrix(X_firstRowArray);
        }
        return data;
    }

    /* A class to convert the unix time to a normal time format. I didn't use this in my app but
     * maybe in the future I will. */
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