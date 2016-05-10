package com.me.rapapp.models.base;

import java.util.List;

/**
 * Created by Lee on 2016/4/12.
 */
public class BaseModel {
    public static final String TAG = "BaseModel";
    protected <T> void dispose(List<T> list)
    {
        if (list != null)
        {
            for (T t : list)
            {
                if (t instanceof ModelInterface)
                {
                    ((ModelInterface) t).dispose();
                }
                t = null;
            }
            list.clear();
            list = null;
        }
    }
}
