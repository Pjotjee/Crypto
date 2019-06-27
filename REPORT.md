## Final Report

# Description:

This application shows information about cryptocurrencies. The first activity
shows a list of cryptocurrencies to choose from. Choosing a coin will lead
the user to the second activity. Here the user will see a graph with past and
forecasted values of the chosen coin. Under these values there is a news button.
This button will bring the user to the last activity, were he sees a list of
news articles related to the coin.

/images/ScreenShot.png

# Technical Design

There are three Activities in this application.
1. MainActivity: The list with Crypto coins
2. DataActivity: The coin forecast overview
3. NewsActivity: The list with news articles of the coin.

The MainActivity only has an onCreate() method that calls on CoinAdapter(),
this CoinAdapter fills all the list entries with a getView() method.

1. MainActivity
    MainActivity()
    - onCreat()
    CoinAdapter()
    - getView()
