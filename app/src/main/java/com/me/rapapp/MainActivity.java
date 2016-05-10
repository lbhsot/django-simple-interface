package com.me.rapapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.me.rapapp.adapter.ListAdapter;
import com.me.rapapp.models.Root;
import com.me.rapapp.utils.ParseUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.me.rapapp.R.color.colorPrimaryDark;

public class MainActivity extends Activity {
    private ListAdapter adapter;
    private ListView listView;
    private TextView sort;
    private int type = 1;   //1是按照更新时间排序    其他为按照更新用户排序

    private List<Root> modelList = new ArrayList<Root>();

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ListAdapter(this, modelList, listView);
        listView.setAdapter(adapter);
        sort = (TextView) findViewById(R.id.sortText);
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 1) {
                    type = 2;
                    sort.setText("更新人");
                } else {
                    type = 1;
                    sort.setText("更新时间");
                }
                initData(type);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (type == 1){
            sort.setText("更新时间");
        }else {
            sort.setText("更新人");
        }
        initData(type);
    }

    private void initData(int type){
        dialog = ProgressDialog.show(this, "", "加载中，请稍后...");
        ParseUtils.listService.getListView("" + type)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//                        if (dialog != null){
//                            dialog.dismiss();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d("MainActivityError", e.getMessage());
//                        if (dialog != null){
//                            dialog.dismiss();
//                        }
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        Log.d("MainActivityNext", s);
//                    }
//                });
                .subscribe(new Subscriber<List<Root>>() {
                    @Override
                    public void onCompleted() {
                        if (dialog != null){
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "未知错误！！！" + e.getMessage(), Toast.LENGTH_LONG).show();
                        if (dialog != null){
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onNext(List<Root> roots) {
                        Log.d("MainActivityNext", "" + roots.size());
                        modelList = roots;
                        adapter.setModelList(modelList);
//                        listView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    }
                });
    }
}
