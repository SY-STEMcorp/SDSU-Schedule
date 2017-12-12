package com.example.root.sdsu_gmap;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by root on 11/17/17.
 */

public class NetworkCommunicator extends AsyncTask<Void, Void, String> {

    private String url = "";

    NetworkCommunicator(String url) {
        this.url = url;
    }

    @Override
    protected String doInBackground(Void... params) {

        try {
            URL LoginURL = new URL(url);
            //TODO: Convert this to https (HttpsURLConnection)
            HttpURLConnection conn = (HttpURLConnection) LoginURL.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder content = new StringBuilder();

            String line;
            while((line = in.readLine()) != null)
            {
                content.append(line);
            }

            return content.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    @Override
    protected void onPostExecute(final String success) {
        super.onPostExecute(success);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}