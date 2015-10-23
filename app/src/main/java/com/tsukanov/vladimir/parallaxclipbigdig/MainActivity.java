package com.tsukanov.vladimir.parallaxclipbigdig;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import loader.json.JSONfunctions;
import parallaxed.ParallaxListView;

public class MainActivity extends AppCompatActivity {

    private static String bigdigInfo = "http://ellotv.bigdig.com.ua/api/home/video";
    static String PICTURE = "picture";
    static String TITLE = "title";
    static String NAME = "name";
    static String VIEW_COUNT = "view_count";

    private ParallaxListView mListView;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arrayList;

    public MainActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_multiple_parallax);
        mListView = (ParallaxListView) findViewById(R.id.list_view);

        new DownloadJSON().execute();
    }

    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(MainActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Android JSON Parse Tutorial");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create an array
            arrayList = new ArrayList<HashMap<String, String>>();
            // Retrieve JSON Objects from the given URL address
            JSONObject jsonObject = JSONfunctions
                    .getJSONfromURL(bigdigInfo);

            try {
                // Locate the array name in JSON
                JSONObject dataJSONObject = jsonObject.getJSONObject("data");
                JSONArray jsonArray = dataJSONObject.getJSONArray("items");
                for (int i = 0; i < jsonArray.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    JSONObject itemObject = jsonArray.getJSONObject(i);
                    // Retrive JSON Objects
                    map.put(TITLE, itemObject.getString(TITLE));
                    JSONArray artistArray = itemObject.getJSONArray("artists");
                    if(artistArray.length() == 0) {
                        map.put(NAME, "Неизвестно");
                    } else {
                        map.put(NAME, artistArray.getJSONObject(0).getString(NAME));
                    }
                    map.put(VIEW_COUNT, itemObject.getString(VIEW_COUNT));
                    map.put(PICTURE, itemObject.getString(PICTURE));
                    // Set the JSON Objects into the array
                    arrayList.add(map);
                }
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {

            // Pass the results into CustomListAdapter.java
            CustomListAdapter adapter = new CustomListAdapter(MainActivity.this, arrayList);
            // Set the adapter to the ListView
            mListView.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }
}
