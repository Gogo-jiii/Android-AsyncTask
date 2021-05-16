package com.example.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class PostAsyncTask extends AsyncTask<String, Void, String> {

    private MainActivity activity;
    private AsyncCallBack asyncCallBack;
    private ProgressBar progressBar;

    PostAsyncTask setInstance(Context context, ProgressBar progressBar) {
        this.activity = (MainActivity) context;
        asyncCallBack = (AsyncCallBack) context;
        this.progressBar = progressBar;
        return this;
    }

    @Override protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override protected String doInBackground(String... strings) {

        String response = "";
        BufferedReader reader = null;
        try {
            URL url = new URL("https://reqres.in/api/users");
            String data = strings[0];
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            response = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                activity.runOnUiThread(new Runnable() {
                    @Override public void run() {
                        progressBar.setVisibility(View.GONE);
                    }
                });
                reader.close();
            } catch (Exception ex) {
            }
        }
        return response;
    }

    @Override protected void onPostExecute(String result) {
        super.onPostExecute(result);
        asyncCallBack.setResult(result);
    }

}
