package com.inbm.inbmstarter.inbm;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bumptech.glide.Glide;

import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by inbm on 2017. 3. 24..
 */

public abstract class AbsBitmapMerge {
    protected Bitmap bitmap;
    Context context;

    OnJsonLoadListener listener;
    protected WeakReferenceHandler handler;
    protected OnException exception;


    public interface OnException{
        void because(String message);
    }

    public abstract <T> T parse(Bitmap bitmap) throws JSONException, IOException;


    public interface OnJsonLoadListener {
        <T> void onJsonLoad(T t) throws IOException, PackageManager.NameNotFoundException;
    }

    public AbsBitmapMerge setExceptionListener(OnException listener){
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

    //    public AbsWeb progressStart(AbsWeb4Progess.OnProgressStart onProgressStart) {
//        this.onProgressStart = onProgressStart;
//        return this;
//    }
//    public AbsWeb progressEnd(AbsWeb4Progess.OnProgressEnd onProgressEnd) {
//        this.onProgressEnd = onProgressEnd;
//        return this;
//    }
    public AbsBitmapMerge(final OnJsonLoadListener listener, Context context)  {

        this.context = context;

        handler = new WeakReferenceHandler(this){
            public void handleMessage(Message msg){

                try{
                    super.handleMessage(msg);
                    listener.onJsonLoad(parse(bitmap));
                } catch(Exception e){
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

                    ArrayList<String> urls = getURLS();

                    ArrayList<Bitmap> bitmaps = new ArrayList<>();
                    for(String s:urls){
                        bitmaps.add(url2Bitmap(s));
                    }
                    bitmap = mergeMultiple(bitmaps.toArray(new Bitmap[bitmaps.size()]));
//                    onProgressEnd.onProgressEnd();
                    handler.sendEmptyMessage(0);
                } catch (Exception e) {
                    if(exception != null) {
                        exception.because(e.getMessage());
                    }
                }
            }
        }).start();
    }

    protected abstract ArrayList<String> getURLS();

    private Bitmap url2Bitmap(String url) throws ExecutionException, InterruptedException {
        return Glide.with(context).asBitmap().load(url).submit().get();
    }


    public static Bitmap mergeMultiple(Bitmap[] parts){



        Bitmap result = Bitmap.createBitmap(parts[0].getWidth() * 2, parts[0].getHeight() * 2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();

//        if(parts.length ==1){
//            result = parts[0];
//        }

        if(parts.length == 2){

            Bitmap src_left = parts[0];
            Bitmap src_right = parts[1];

            Matrix matrix = new Matrix();
            matrix.postScale(2f, 2f);
            Bitmap resized_left = Bitmap.createBitmap(src_left, 0, 0, src_left.getWidth(), src_left.getHeight(), matrix, true);
            Bitmap resized_right = Bitmap.createBitmap(src_right, 0, 0, src_right.getWidth(), src_right.getHeight(), matrix, true);

            Bitmap left = Bitmap.createBitmap(resized_left,
                    resized_left.getWidth()/4
                    , 0
                    , resized_left.getWidth()/2
                    , resized_left.getHeight());

            Bitmap right = Bitmap.createBitmap(resized_right,
                    resized_right.getWidth()/4
                    , 0
                    , resized_right.getWidth()/2
                    , resized_right.getHeight());



            canvas.drawBitmap(left, 0, 0, paint);
            canvas.drawBitmap(right, result.getWidth()/2, 0, paint);
            if(!src_left.isRecycled()){
                src_left.recycle();
                src_left = null;
            }

            if(!src_right.isRecycled()){
                src_right.recycle();
                src_right = null;

            }

        } else if(parts.length == 3) {
            Bitmap src_left_top = parts[0];
            Bitmap src_left_bottom = parts[1];
            Bitmap src_right = parts[2];

            Matrix matrix = new Matrix();
            matrix.postScale(2f, 2f);
            Bitmap resized_right = Bitmap.createBitmap(src_right, 0, 0, src_right.getWidth(), src_right.getHeight(), matrix, true);

            Bitmap right = Bitmap.createBitmap(resized_right,
                    resized_right.getWidth()/4
                    , 0
                    , resized_right.getWidth()/2
                    , resized_right.getHeight());





            canvas.drawBitmap(src_left_top, 0, 0, paint);
            canvas.drawBitmap(src_left_bottom, 0, src_left_bottom.getHeight(), paint);
            canvas.drawBitmap(right, result.getWidth()/2, 0, paint);

            if(!src_left_top.isRecycled()){
                src_left_top.recycle();
                src_left_top =null;
            }

            if(!src_left_bottom.isRecycled()){
                src_left_bottom.recycle();
                src_left_bottom =null;

            }

            if(!src_right.isRecycled()){
                src_right.recycle();
                src_right =null;

            }

        } else if(parts.length <= 4) {
            for (int i = 0; i < 4; i++) {
                canvas.drawBitmap(parts[i], parts[i].getWidth() * (i % 2), parts[i].getHeight() * (i / 2), paint);
                Log.e("com/inbm/inbmstarter/inbm", i + ":" + parts[i].getWidth() * (i % 2) + "X" + parts[i].getHeight() * (i / 2));
            }
        }

        Log.e("com/inbm/inbmstarter/inbm", "->" + result.getWidth());
        return result;
    }


}