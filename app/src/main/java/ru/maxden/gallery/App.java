package ru.maxden.gallery;

import android.app.Application;
import android.content.Context;

import ru.maxden.gallery.data.network.NetworkBuilder;
import ru.maxden.gallery.data.network.RetrofitService;


public class App extends Application {

    private RetrofitService retrofitService;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitService = NetworkBuilder.initService();
    }

    public static App get (Context context){
        return (App) context.getApplicationContext();
    }

    public RetrofitService getService() {
        return retrofitService;
    }
}
