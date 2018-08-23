package com.example.dell.proyecto.aplication;

import android.app.Application;
import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.dell.proyecto.utils.AsyncConector;

public class JSONAplication extends Application {
    private  final static String URL = "http://localhost:3000";


    public  void getData(Context context, ArrayAdapter<String> adapter){
        new AsyncConector(context, adapter, URL).execute();
    }
}
