package com.example.cryptoforecast;

import android.content.Context;
import android.util.Log;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


class DataRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;                                    // context to send internet requests
    private Callback activity;                                  // callback to notify the activity
    private ArrayList<Integer> dataMin = new ArrayList<Integer>();   // arrayList for the JSON elements


    //** declaration of callback to notify the activity */
    interface Callback {
        void gotData(ArrayList<Integer> dataMin);
        void gotDataError(String message);
    }
    DataRequest(Context context) {

        this.context = context;
    }

    //** retrieve the categories from the API and notify the activity
    // that instantiated the request that it is done through the callback*/
    void getData(Callback activity) {
        // Saves activity as instance variable
        this.activity = activity;
        // Create new request queue for API requests
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://min-api.cryptocompare.com/data/histominute?fsym=BTC&tsym=GBP&limit=10";
        String ApiKey = "&api_key=635b4471780f89cd7d72b4afbfc10cde1157354272a6583e849964c523a7b75b";
        // Creates new request and adds it to queue
        String url_total = url + ApiKey;
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url_total, null, this, this);
            queue.add(jsonObjectRequest);
        }
        catch(Exception error) {
            Log.e("error", error.getMessage());
        }

    }

    //** message in case of an error */
    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotDataError(error.getMessage());
    }

    //** put the response JSONObject into an arraylist if succesfull */
    @Override
    public void onResponse(JSONObject response) {
        try {
            // initialize the JSON array and create a new ArrayList of strings
            JSONArray dataArray = response.getJSONArray("data");
            //ArrayList<String> data = new ArrayList<>();
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject dataObject = dataArray.getJSONObject(i);
                int dataM = dataObject.getInt("Data");
                dataMin.add(dataM);
            }
            activity.gotData(dataMin);
        }
        // call the error method when JSONException occur
        catch(JSONException error){
            activity.gotDataError(error.getMessage());
        }

    }
}