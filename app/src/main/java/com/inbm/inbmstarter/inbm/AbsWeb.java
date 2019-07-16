package com.inbm.inbmstarter.inbm;

import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.InetAddress;

/**
 * Created by inbm on 2017. 3. 24..
 */

public abstract class AbsWeb {
    protected String html = "";

    OnJsonLoadListener listener;
    protected WeakReferenceHandler handler;
    protected OnException exception;
    protected AbsWeb4Progess.OnProgressStart onProgressStart;
    protected AbsWeb4Progess.OnProgressEnd onProgressEnd;

    public interface OnException{
        void because(String message);
    }

    public abstract <T> T parse(String html) throws JSONException, IOException;


    public interface OnJsonLoadListener {
        <T> void onJsonLoad(T t) throws IOException, PackageManager.NameNotFoundException;
    }

    public AbsWeb setExceptionListener(OnException listener){
        this.exception = listener;
        return this;
    }

    protected interface OnProgressStart{
        void onProgressStart();
        //progress Start
    }

    protected interface OnProgressEnd{
        void onProgressEnd();
        //progress End
    }

    //서버 다녀올 때 오류나면 얘 사용!
    // If you get an error when you go to the server, use it!
    public void because(OnException exception){
        //사용법 : Get/Post방식 뒤에 .because(c -> _log.e(c));
        //서버에 다녀올 때 오류가 난다면 오류를 띄워준다.
        this.exception=exception;
    }

    static class WeakReferenceHandler extends Handler
    {
        WeakReference<Object> reference;

        WeakReferenceHandler(Object o)
        {
            reference = new WeakReference<Object>(o);
        }
    }

    public AbsWeb progressStart(AbsWeb4Progess.OnProgressStart onProgressStart) {
        this.onProgressStart = onProgressStart;
        return this;
    }
    public AbsWeb progressEnd(AbsWeb4Progess.OnProgressEnd onProgressEnd) {
        this.onProgressEnd = onProgressEnd;
        return this;
    }
    public AbsWeb(final OnJsonLoadListener listener )  {
        handler = new WeakReferenceHandler(Looper.getMainLooper()){
            public void handleMessage(Message msg){
                try{
                    listener.onJsonLoad( parse(html));
                    super.handleMessage(msg);
                } catch(Exception e){
                    _log.e(e.getMessage());
                    e.printStackTrace();

                    if(exception != null){
                        exception.because(e.getMessage());
                    }
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    onProgressStart.onProgressStart();
                    html = getHTML(getURL() );
//                    onProgressEnd.onProgressEnd();
                    handler.sendEmptyMessage(0);
                } catch (Exception e) {
                    _log.e(e.getMessage());
                    e.printStackTrace();

                    if(exception != null) {
                        exception.because(e.getMessage());
                    }
                }
            }
        }).start();
    }
    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }
    protected abstract String getHTML(String url) throws IOException;
    protected abstract String getURL();
}
