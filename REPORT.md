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

The MainActivity() has an onCreate() method that calls on CoinAdapter(),
this CoinAdapter() fills all the list entries with a getView() method.
The setOnItemClickListener() is called when the user clicks on a coin in them
list, and it will start the DataActivity().

1. MainActivity

    MainActivity()
    - onCreat()

    onCreate()
    - CoinAdapter()
    - setOnItemClickListener()

The CoinAdapter() method sets the right values for the items in the list.

1.1 CoinAdapter()
    - getView()

The DataActivity() has a onCreate() method that set some text fields, it also
calls on the DataRequest() method to receive the historical data of the coin.
Then the gotData() or gotDataError() method will be called. The gotData() method
will call the DataForcast() method and this will give the forecasted values back.
Than gotData() will make the graph and set all the values in the TextFields.


2. DataActivity

    DataActivity()
    - onCreate()
    - gotData()
    - gotDataError()
    - onClick()

    onCreate()
    - DataRequest()

    gotData()
    - DataForecast()
    - setValues...

    gotDataError()

    onClick()

The DataRequest() method requests the data from the API, and gets an object back
the onRespons() method handles the object send back.  

2.1 DataRequest()

    DataRequest()
    - Callback()
        - gotData()
        - gotDataError()
    - jsonObjectRequest()

    onErrorResponse()

    onResponse()
    - getJSONArray()
    - gotData()
    - gotDataError

DataForcast() takes an arrayList with past values, and makes a forecast model.
Than uses that model to create an arrayList with the forecasted values, and
returns it.     


2.2 DataForecast()

    DataForcast()


The NewsActivity() gets a coin from the data activity and starts DownLoadNews()
to get the articles from the API. Than after that the onPostExecute() method
will put the articles in the ListNewsAdapter().

3. NewsActivity

    NewsActivity()
    - onCreate()
    - DownLoadNews()
    - onPostExecute()

    onCreate()
    - DownloadNews()

    DownLoadNews()
    - onPreExcecute()
    - doInBackground()
    - NewsRequest()

    onPostExecute()
    - JSONObject()
    - ListNewsAdapter()
    - setOnItemClickListener()

NewsRequest() Checks for the connection and than get's the articles.    

3.1 NewsRequest

    NewsRequest()
    - isNetworkAvailable()
    - executeGet()

NewsDetailActivity() starts an activity for every news article.

3.2 NewsDetailActivity

    NewsDetailActivity()
    - onCreate()

ListNewsAdapter() put's all the articles in a list.     

3.3 ListNewsAdapter

    ListNewsAdapter()
    - getCount()
    - getView()

    ListNewsHolder()


## Challenges

The three most difficult challenges I faced were to work with OkHTTP, work with
a library that made the CandleChartGraph. And lastly to work with a library the
made all the calculations for the model.
    I still don't really understand OkHTTP that much, I mainly got it working by
just googling the errors and looking at other examples on the internet.
But I am not sure if I could reproduce another OkHttp request right now.
    The CandleChartGraph gave me the impression that it had some buggs in the
graph itself. One example: I wanted to show element 1 until 30 (from a list of
100 elements) gave no problem. But showing element 40 until 70 messed up the
whole graph. And of course it could be that I made some mistakes somewhere. But
I am not sure yet.
    With the library for the calculations I had a lot of problems with the
documentation of the library. Especially finding methods to do calculations
and the syntax of those methods. In the future I am definitely going to avoid
doing mathematics in Java. I prefer python for these things. Doing math in java
gave me nightmares.
    What I learned most of this project that it is really hard to manage the time.
In one time I can write a lot of code in an hour. But when I encounter a bug
I could be busy writing just one or two lines a day.

## Decisions

Most of the decisions I made were features I didn't implement because of time
constraints. There were also changes in API's. Firstly I wanted to get the data
from bittrex, but I had trouble with the documentation. And it also didn't
have a lot of free features. Therefore I changed to coinAPI, this one had
more free features and the documentation was very clear. Especially since I am
not a pro App builder (yet) a clear documentation with a lot of examples helps
a lot. Given more time I would keep my owns database with Historical coin values
on a server. I would make the calculations in a python script that would
interact with this database for the forecast values. And I would only use the
App to receive the information from that database. 
