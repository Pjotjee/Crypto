package com.example.cryptoforecast;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;

class DataForecast {

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

    private SimpleMatrix X, XXt_inv,b,y, y_hat,e,Xn,XXt_invn, bn;

    public DataForecast(ArrayList<Double> dataLow, ArrayList<Double> dataHigh, ArrayList<Double> dataTime, ArrayList<Double> dataOpen, ArrayList<Double> dataClose) {

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
        // b= (X'X)^-1 *X' y
        bn = XXt_invn.mult(Xn.transpose().mult(y));

    }

}
