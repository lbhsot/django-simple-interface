package com.me.rapapp.adapter.layout;

import android.content.Context;
import android.widget.TextView;

import com.me.rapapp.R;
import com.me.rapapp.adapter.base.BaseLinearLayout;
import com.me.rapapp.models.Root;

/**
 * Created by Lee on 2016/5/9.
 */
public class ListItemLayout extends BaseLinearLayout {
    private TextView updateTime, version, project, updateUser, description;
    private Context context;

    public ListItemLayout(Context context) {
        super(context, R.layout.list_item);
        this.context = context;
        initView();
    }

    private void initView(){
        updateTime = (TextView) findViewById(R.id.update_time);
        version = (TextView) findViewById(R.id.version);
        project = (TextView) findViewById(R.id.project);
        updateUser = (TextView) findViewById(R.id.update_user);
        description = (TextView) findViewById(R.id.description);
    }

    public void setData(Root model){
        updateTime.setText(model.getUpdate_time());
        version.setText(model.getVersion());
        project.setText(model.getProject());
        updateUser.setText(model.getUpdate_user());
        description.setText(model.getDescription());
    }
}
