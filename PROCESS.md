# Crypto
Android Application to forecast cryptocurrencies


# Day 1 Monday 3 - June

Today I finished the Proposal, created the Github repository and the README.

I started and finished the sketch.

### Sketch
![Proposal Sketch](/images/SketchApp.png)


# Tuesday 4 - June

I started with the sketch Design

### Sketch
![Proposal Sketch](/images/DesignSketch.png)

# Wednesday 5 - June

I finished the sketch Design, after that I started with the first screen.
The problems I faced were:
* Getting a hang of Android Studio again
* How to put in different images in different rows in the list.
** For every crypto coin I want to put their own Icon in the list.

# Thursday 6 - June

I decided to implement the pictures later, since they are not important for the prototype yet.
Today I found out there is not a graph available in my android Studio
So I have to figure out which one I want to use from where.
Ill find out the graph later, first I should make a connection with an API for the data.
The bittrex API is too much of a hassle before I can get a key.
New API comes from CoinAPI.io and gives 100 request per day for free.

# Friday 7 - June

I started working with the API from CoinAPI, I have to use OkHttp to request data instead of Volley.
I worked with volley previously, but not yet with OkHttp.
The data request works.
Now I will make the news request
I found a tutorial for a news app: https://androstock.com/tutorials/create-a-news-app-on-android-android-studio.html
I will use that to make my won news screen.
The news screen works.
I hereby declare the prototype as finished.

# Tuesday 11 - June

I will check for some other API's for the data and compare them with CoinAPI.
So I know that I have the one that suits me best.
I think that the I will fetch the data from CryptoCompare.
e.g. https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=USD,JPY,EUR
Since it has better documentation, and more free options.
I can get 100.000 requests per month for free
