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

# Wednesday 12 - June

We agreed to use https://google.github.io/styleguide/javaguide.html as a reference to our styleguide
TAB IS 4 SPACES
in the else statements: the closing brace has its own line
Docstring at the beginning of the class /*   \*/
and after that just line comments in the code using the // format
no need to comment on variables like String name //bla bla bla

# Thursday 13 - June

I wil continue to work on the data request
The data request seem to work fine now, after some help from Renske
Now I will choose on a graph to show the data in.
AnyChart has caught my attention since it seem to show nice candle graphs.
MPandroidChart seems to be even better.

# Friday 14 - June

I have a nice candle chart working now, it's not exactly as I want it to be but ill fix that next week.
Today I will try to get the forecast done.
The calculations of the forecast are finished.
I used ejml library for the mathematics.
I only need to return the forecasted data to the other activity.
I declare the alpha version as finished.

# Monday 17 - June

I want to finish my list in the first screen today.
And see how I can display the time in hours and minutes.


# Saturday 22 - June

I finally finished all the forecasts, there were a lot of troubles with the syntax of the libraries that I used.
The CandleChart doesn't seem to plot certain indices. e.g. time 0 till 100 no problem, time 50 till 150 doesn't plot

# Sunday 23 - June

I will make all good colors. And set all the correct fields with the right values.

# Monday 24 - June

Did a lot on the style and colors of DataForcast.
