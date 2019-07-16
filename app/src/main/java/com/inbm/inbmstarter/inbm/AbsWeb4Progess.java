package com.inbm.inbmstarter.inbm;

import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * Created by inbm on 2017. 3. 24..
 */

public abstract class AbsWeb4Progess {
    protected String html = "";

    OnJsonLoadListener listener;
    protected WeakReferenceHandler handler;
    protected OnException exception;
    protected OnProgressStart onProgressStart;
    protected OnProgressEnd onProgressEnd;

    public interface OnProgressStart{
        void onProgressStart();
        //progress Start
    }

    public interface OnProgressEnd{
        void onProgressEnd();
        //progress End
    }


    public interface OnException{
        void because(String message);
    }

    public abstract <T> T parse(String html) throws JSONException, IOException;


    public interface OnJsonLoadListener {
        <T> void onJsonLoad(T t) throws IOException, PackageManager.NameNotFoundException;
    }

    public AbsWeb4Progess setExceptionListener(OnException listener){
        this.exception = listener;
        return this;
    }

    //서버 다녀올 때 오류나면 얘 사용!
    public AbsWeb4Progess because(OnException exception){
        //사용법 : Get/Post방식 뒤에 .because(c -> _log.e(c));
        //서버에 다녀올 때 오류가 난다면 오류를 띄워준다.
        this.exception=exception;
        return  this;
    }

    public AbsWeb4Progess progressStart(OnProgressStart onProgressStart) {
        this.onProgressStart = onProgressStart;
        return this;
    }
    public AbsWeb4Progess progressEnd(OnProgressEnd onProgressEnd) {
        this.onProgressEnd = onProgressEnd;
        return this;
    }
    static class WeakReferenceHandler extends Handler
    {
        WeakReference<Object> reference;

        WeakReferenceHandler(Object o)
        {
            reference = new WeakReference<Object>(o);
        }
    }

    public AbsWeb4Progess(final OnJsonLoadListener listener )  {
        handler = new WeakReferenceHandler(Looper.getMainLooper()){
            public void handleMessage(Message msg){

                try{
                    listener.onJsonLoad( parse(html));
                    super.handleMessage(msg);
                } catch(Exception e){
                    if(exception != null)
                        exception.because(e.getMessage());
                }
            }
        };


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    onProgressStart.onProgressStart();
                    html = getHTML(getURL() );
                    onProgressEnd.onProgressEnd();
                    handler.sendEmptyMessage(0);
                } catch (Exception e) {
                    if(exception != null)
                    exception.because(e.getMessage());
                }
            }
        }).start();
    }


    protected abstract String getHTML(String url) throws IOException;
    protected abstract String getURL();


}