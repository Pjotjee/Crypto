# Crypto
Android Application to forecast cryptocurrencies

https://youtu.be/XbI466CcXYU


## Problem Statement

People who are interested in crypto currencies like Bitcoin often like to know how the price will move in the future.
There are not a lot of apps on the market who provide these predictions.
Most predictions come in the form of a strategy telling the user to buy or sell based on their prediction.
But don't give a clear graph of how the coin will behave in the future.

## Solution
This app will give a prediction of the selected crypto coin displayed in a graph.
The prediction depends on the user's selected confidence percentage (90% , 95%, etc).
This will allow the user to implement their own strategy given the prediction.

### Sketch
![Proposal Sketch](/images/SketchApp.png)

### Main Features
* MVP
  * From a list of 10 crypto coins a coin can be selected.
  * Then a new screen will be show with a graph of the selected coins.
  * The graph will be based on a ARMA(p,q) model for timeseries, so purely based on values in the past.
  * A percentage can be selected, and a time.
  * A button on the bottom can be selected to brings the user to a new screen with the latest news that might indicate another prediction.
* Optional
  * Expand the list of crypto coins to choose from.
  * Expand the model for better predictions.
  * Expand the predictions from only the 24 hour to monthly, or yearly predictions.
  * Expand the data from multiple exchanges.

## Prerequisites
* Data of the past values need to be requested using an API from Bittrex.
  * API https://api.bittrex.com/api/v1.1
* After that the data has to be transformed using the model.
* Data from the news also needs to be requested from an API from google.
  * API: https://newsapi.org/v2/everything?q=bitcoin&apiKey=API_KEY
