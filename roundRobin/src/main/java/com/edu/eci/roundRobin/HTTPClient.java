package com.edu.eci.roundRobin;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HTTPClient {
    private OkHttpClient httpClient;
    private String baseUrl="http://";
    private String[] ports={"ec2-3-91-176-176.compute-1.amazonaws.com:8001", "ec2-54-86-67-105.compute-1.amazonaws.com:8001"};
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private int serverNumber=0;

    public HTTPClient() {
        httpClient = new OkHttpClient();
    }

    public String getMessages(String path) throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl+ports[serverNumber]+path)
                .get()
                .build();
        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }

    public void changePort(){
        this.serverNumber= (this.serverNumber+1)% ports.length;
    }

}
