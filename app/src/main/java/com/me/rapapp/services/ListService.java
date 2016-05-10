package com.me.rapapp.services;

import com.me.rapapp.models.Root;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Lee on 2016/5/9.
 */
public interface ListService {
    @GET("{type}")
    Observable<List<Root>> getListView(@Path("type") String type);
}
