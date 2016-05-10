package com.me.rapapp.utils;

import com.me.rapapp.services.ListService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lee on 2016/5/9.
 */
public class ParseUtils {
    private String baseUrl = "http://192.168.0.50:8000/";

    public static ListService listService = new Retrofit.Builder().baseUrl("http://192.168.0.50:8000/")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build().create(ListService.class);

}
