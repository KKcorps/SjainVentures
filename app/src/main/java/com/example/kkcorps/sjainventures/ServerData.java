package com.example.kkcorps.sjainventures;

import android.app.ListActivity;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 4/12/14.
 */
public class ServerData extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new HttpGetTask().execute();
    }

    private class HttpGetTask extends AsyncTask<Void, Void, List<String> > {

        private String HOST = "dmsinfosystem.com";
        private String GET_URL = "http://test.dmsinfosystem.com/get/";

        private static final String TAG = "HttpGet";
        AndroidHttpClient newClient = AndroidHttpClient.newInstance("");

        @Override
        protected List<String> doInBackground(Void... params) {
            HttpGet request = new HttpGet(GET_URL);
            JSONResponseHandler responseHandler = new JSONResponseHandler();

            try {
                return newClient.execute(request, responseHandler);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<String> result) {
            if (null != newClient)
                newClient.close();
            setListAdapter(new ArrayAdapter<String>(
                    ServerData.this,
                    R.layout.list_view, result));


        }
    }

        private class JSONResponseHandler implements ResponseHandler<List<String>> {

            private static final String LAST_NAME_TAG = "lastname";
            private static final String FIRST_NAME_TAG = "firstname";
            private static final String CONTACT_TAG = "phone";
            private static final String TAG = "JSON";
            @Override
            public List<String> handleResponse(HttpResponse response)
                    throws ClientProtocolException, IOException {
                List<String> result = new ArrayList<String>();
                String JSONResponse = new BasicResponseHandler()
                        .handleResponse(response);
                try {

                    // Get top-level JSON Object - a Map
                    JSONArray earthquakes = (JSONArray) new JSONTokener(
                            JSONResponse).nextValue();


                    // Extract value of "earthquakes" key -- a List
                    //JSONArray earthquakes = responseObject.getJSONArray("");

                    // Iterate over earthquakes list
                    for (int idx = 0; idx < earthquakes.length(); idx++) {

                        // Get single earthquake data - a Map
                        JSONObject earthquake = (JSONObject) earthquakes.get(idx);

                        // Summarize earthquake data as a string and add it to
                        // result
                        String jsonString = FIRST_NAME_TAG + ":"
                                + earthquake.get(FIRST_NAME_TAG) + ","
                                + LAST_NAME_TAG + ":"
                                + earthquake.getString(LAST_NAME_TAG) + ","
                                + CONTACT_TAG + ":"
                                + earthquake.get(CONTACT_TAG);

                        result.add(jsonString);
                        Log.i(TAG, jsonString);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return result;
            }
        }
    }

