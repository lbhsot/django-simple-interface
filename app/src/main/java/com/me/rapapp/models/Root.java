package com.me.rapapp.models;

import com.me.rapapp.models.base.BaseModel;
import com.me.rapapp.models.base.ModelInterface;

/**
 * Created by Lee on 2016/5/9.
 */
public class Root extends BaseModel implements ModelInterface{
    private String update_time;

    private String version;

    private String project;

    private String description;

    private String update_user;

    public void setUpdate_time(String update_time){
        this.update_time = update_time;
    }
    public String getUpdate_time(){
        return this.update_time;
    }
    public void setVersion(String version){
        this.version = version;
    }
    public String getVersion(){
        return this.version;
    }
    public void setProject(String project){
        this.project = project;
    }
    public String getProject(){
        return this.project;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setUpdate_user(String update_user){
        this.update_user = update_user;
    }
    public String getUpdate_user(){
        return this.update_user;
    }

    @Override
    public void dispose() {

    }
}