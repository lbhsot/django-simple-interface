package com.me.rapapp.adapter.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.me.rapapp.models.base.ModelInterface;

import java.util.Collection;
import java.util.List;

/**
 * Created by Lee on 2016/4/12.
 */
public class BaseListAdapter extends ArrayAdapter<ModelInterface> implements AdapterInterface<ModelInterface> {

    private ListView listView;

    public BaseListAdapter(Context context, List<ModelInterface> list, ListView listView){
        super(context, 0, list);
        this.listView = listView;
        list = null;
        context = null;
        listView = null;
    }

    @Override
    public ModelInterface getItem(int position) {
        return super.getItem(position);
    }

    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void addAll(Collection<? extends ModelInterface> collection)
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
        {
            for (ModelInterface model : collection)
            {
                super.add(model);
            }
        }
        else
        {
            super.addAll(collection);
        }
    }

    @Override
    public ModelInterface get(int position)
    {
        return (ModelInterface) listView.getAdapter().getItem(position);
    }

    @Override
    public int getModelCount()
    {
        return getCount();
    }

    @Override
    public void bind(View view)
    {
        ((ListView) view).setAdapter(this);
    }

    @Override
    public void clear()
    {
        ModelInterface model;
        int count = getCount();
        for (int i = 0; i < count; i++)
        {
            model = getItem(i);
            model.dispose();
            model = null;
        }
        super.clear();
    }

    @Override
    public void dispose() {

    }
}
