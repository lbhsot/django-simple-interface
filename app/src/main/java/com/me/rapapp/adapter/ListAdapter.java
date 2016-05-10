package com.me.rapapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.me.rapapp.adapter.base.AdapterInterface;
import com.me.rapapp.adapter.base.BaseListAdapter;
import com.me.rapapp.adapter.layout.ListItemLayout;
import com.me.rapapp.models.Root;
import com.me.rapapp.models.base.ModelInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2016/5/9.
 */
public class ListAdapter extends BaseListAdapter implements AdapterInterface<ModelInterface> {
    private Context context;
    private List<Root> modelList = new ArrayList<Root>();

    public ListAdapter(Context context, List<Root> list, ListView listView) {
        super(context, new ArrayList<ModelInterface>(), listView);
        this.context = context;
        this.modelList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemLayout view = getConvertView(convertView);
        Root model = (Root) getItem(position);
        view.setData(model);
        model = null;
        return view;
    }

    private ListItemLayout getConvertView(View convertView)
    {
        return (convertView != null && convertView instanceof ListItemLayout) ?
                (ListItemLayout) convertView : new ListItemLayout(getContext());
    }

    @Override
    public Root getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    public void setModelList(List<Root> modelList){
        this.modelList = modelList;
        this.notifyDataSetChanged();
    }
}
