package com.sofrecom.stage.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpClientMain {
    private String endPoint;
    private String result;
    private HttpURLConnection connection;
    private BufferedReader reader;
    private StringBuffer responseContent;
    private int status;
    public HttpClientMain(String endPoint){
        responseContent = new StringBuffer();
        this.endPoint = endPoint;
    }

    public void init(){
       try{
           URL url = new URL(this.endPoint);
           connection = (HttpURLConnection)url.openConnection();
           connection.setRequestMethod("GET");

           connection.setConnectTimeout(8000);
           connection.setReadTimeout(8000);
           this.status = connection.getResponseCode();
       }
       catch(Exception e){
           System.out.println(e);
       }
    }

    public void closeConnection(){
        connection.disconnect();
    }

    public void getData(){
       try{
           status = connection.getResponseCode();
           System.out.println(status);
           if(status>299){
               reader = new BufferedReader(new InputStreamReader((connection.getErrorStream())));
               while((result=reader.readLine())!= null){
                   responseContent.append(result);
               }
               reader.close();
           }
           else{
               reader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
               while((result=reader.readLine())!= null){
                   responseContent.append(result);
               }
               reader.close();
           }
       }
       catch(Exception e){
           System.out.println(e);
       }
 ;
       /* JSONArray jsonarray = new JSONArray(responseContent.toString());
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            String classs = jsonobject.getString("class");
            Double score = jsonobject.getDouble("score");
            System.out.println(jsonobject+"\n");
        }*/

     //  System.out.println();
    }

    public String getPostCategory(){
        JSONArray jsonarray = new JSONArray(responseContent.toString());
        System.out.println(responseContent);
        double max = 0;
        String category="";
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            String classs = jsonobject.getString("class");
            Double score = jsonobject.getDouble("score");
            if(score>max){
                category = classs;
                max = score;
            }
            System.out.println(jsonobject+"\n");
        }
        return category;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public JSONArray getJsonData(String responseContent){
        JSONArray prediction = new JSONArray(responseContent);
        /*JSONObject object = prediction.getJSONObject(0);
        System.out.println(prediction);*/
        return prediction;
    }

    public static void main(String[] args) throws IOException {

     //  HttpClientMain httpClientMain = new HttpClientMain("https://imageclassifierpidev.herokuapp.com/getResult");
      /* httpClientMain.init();
       httpClientMain.getData();
       //System.out.println(httpClientMain.getJsonData());
       httpClientMain.closeConnection();*/

    }
}
