package com.sofrecom.stage.utils;

import com.sofrecom.stage.models.Post;
import com.sofrecom.stage.services.forum.IPostService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class PosthttpClient {

    @Autowired
    IPostService postService;

    private String endPoint;
    private String result;
    private HttpURLConnection connection;
    private BufferedReader reader;
    private StringBuffer responseContent;
    private int status;

    public PosthttpClient(){

    }

    public void postRequest(String urlStr, Post post) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        Map<String,String> map = new HashMap<String,String>();
        map.put("commentId",post.getPostId().toString());
        map.put("commentContent",new String( Base64.getEncoder().encodeToString(post.getPostContent())));
        map.put("commentContentType",post.getPostContentType().toString());
        JSONObject jo = new JSONObject(map);


        try {
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(jo.toString().getBytes());
            outputStream.flush();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line.trim());
            }
        } finally {

        }

    }

    /*public static void main(String[] args) throws IOException {

    }*/


}
