package com.me.rapapp.adapter.base;

import android.view.View;

import java.util.Collection;
/**
 * Created by Lee on 2016/4/11.
 */
public interface AdapterInterface<T>{
    public int getModelCount();

    public void dispose();

    public void add(T model);

    public void addAll(Collection<? extends T> modelList);

    public T get(int position);

    public void notifyDataSetChanged();

    public void clear();

    public void bind(View view);
}
