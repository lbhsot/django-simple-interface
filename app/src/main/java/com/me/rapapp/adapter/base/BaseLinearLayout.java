package com.me.rapapp.adapter.base;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Lee on 2016/2/22.
 */
public abstract class BaseLinearLayout extends LinearLayout
{
    public BaseLinearLayout(Context context, int resource) {
        super(context);
        View.inflate(context, resource, this);
    }
}
