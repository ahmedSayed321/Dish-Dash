package com.example.dishdash.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {

    public Service service;
    public static Network INSTANCE = null;

    private Network() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/").addConverterFactory(
                        GsonConverterFactory.create()).build();

         service = retrofit.create(Service.class);

    }

    public static Network getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Network();
        }
        return INSTANCE;
    }
}
