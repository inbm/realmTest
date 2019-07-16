package com.inbm.inbmstarter.inbm;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

/**
 * Created by inbm on 2017. 2. 27..
 */
public class App extends Application {
    private static Context context;

    public static String firebase_token;

    public static boolean isIran;

    @Override
    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();

        //firebase Setting
//        new FirebaseMessagingService().createNotificationChannel();
//        _firebase.setToken();

        //this is to ignore file expose in taking pictures from camera. another solution is using FileProvider
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

//    @Override public void onLowMemory() {
//        super.onLowMemory();
//        Glide.get(this).clearMemory();
//    }
//
//    @Override public void onTrimMemory(int level) {
//        super.onTrimMemory(level);
//        Glide.get(this).trimMemory(level);
//    }

    public static Context getStaticContext() {
        return App.context;
    }

    public static RequestManager getGlideManger() {
        return Glide.with(getStaticContext());
    }

    public static String getFirebase_token() {
        return firebase_token;
    }

    public static void _toast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void _toast(int id) {
        Toast.makeText(context, context.getText(id), Toast.LENGTH_LONG).show();
    }
}