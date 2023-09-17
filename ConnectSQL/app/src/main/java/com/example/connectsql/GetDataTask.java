package com.example.connectsql;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetDataTask extends AsyncTask<Void,Void,String> {
  private MainActivity activity;
    public GetDataTask(MainActivity activity){
        this.activity = activity;
    }
@Override
    protected String doInBackground(Void ...voids){
    try{
    URL url = new URL("http://10.0.2.2:8080/api/data");
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    connection.setRequestProperty("User-Agent", "Chrome");
    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    StringBuffer inputLine = new StringBuffer();
    String line;
    while ((line = in.readLine()) != null){
        inputLine.append(line);
    }
    in.close();
   return inputLine.toString();
} catch (
    MalformedURLException e) {
        return "Url error " + e.getMessage();
    } catch (
    IOException e) {
   return "IO error " + e.getMessage();
    }
}
    protected void onPostExecute(String result){
       activity.res.setText(result);
    }
}
