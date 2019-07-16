package com.inbm.inbmstarter.inbm;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Shared<T> {

    private Gson gson = new GsonBuilder().create();
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String table_name;
    private Type cls;


    Shared(Type cls) {
        this.cls = cls;
        table_name = cls.toString();
        sp = PreferenceManager.getDefaultSharedPreferences(App.getStaticContext());
        editor = sp.edit();
    }

    public String json() {
        return sp.getString(table_name, null);
    }

    public List<T> find() {
        String json = sp.getString(table_name, null);

        if (json != null && json.isEmpty()) {
            return null;
        }
        return gson.fromJson(json, cls);
    }

    public T findNoList() {
        String json = sp.getString(table_name, null);

        if (json != null && json.isEmpty()) {
            return null;
        }
        return gson.fromJson(json, cls);
    }

    public void insert(T t) {
        T datas = (T) find();

        datas = t;
        String json = gson.toJson(datas, cls);
        editor = sp.edit();
        editor.putString(table_name, json);
        editor.apply();
    }

    public void insertOne(T t) {
        List<T> datas = find();
        if (datas == null) {
            datas = new ArrayList<>();
        }
        datas.add(t);
        String json = gson.toJson(datas, cls);
        editor = sp.edit();
        editor.putString(table_name, json);
        editor.apply();
    }

    public void insertMany(List<T> list) {
        List<T> datas = find();
        if (datas == null) {
            datas = new ArrayList<>();
        }
        datas.addAll(list);

        String json = gson.toJson(datas, cls);

        editor = sp.edit();
        editor.putString(table_name, json);
        editor.apply();
    }

    public void deleteOne(String key) {
        editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    public void clear() {
        editor.remove(table_name);
        editor.apply();
    }
}