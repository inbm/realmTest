package com.inbm.inbmstarter.inbm;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


abstract public class 	Downloader {


    private WeakReferenceHandler handler;

    public abstract <T> T postFileDownloaded(File[] files) throws JSONException;

    FileCache fileCache;
    File file;

    File[] files;

    public interface OnDownloaded {
        <T> void onDowndloaded(T t);
    }

    static class WeakReferenceHandler extends Handler {
        WeakReference<Object> reference;

        WeakReferenceHandler(Object o) {
            reference = new WeakReference<Object>(o);
        }
    }

    public Downloader(Context context, final OnDownloaded listener, final String url) {
        fileCache = new FileCache(context);
        file = null;

        handler = new WeakReferenceHandler(this) {
            public void handleMessage(Message msg) {
                try {
                    listener.onDowndloaded(postFileDownloaded(files));
                    super.handleMessage(msg);
                } catch (Exception e) {
                    _log.inCatch("----> " + e.getMessage());
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fileCache.clear();
                    file = fileCache.getFile(url);

                    //if(file == null)
                    download(url);
                    //unzip(file.getAbsolutePath(), fileCache.getDir().getAbsolutePath());

                    String uniqe_name = url.substring(url.lastIndexOf('/') + 1, url.lastIndexOf('.'));
                    _file.doUnzip(file.getAbsolutePath(), fileCache.getDir().getAbsolutePath(), uniqe_name);

                    String f = fileCache.getDir().getAbsolutePath() + File.separator + uniqe_name;
                    File dir = new File(f);

                    //files = fileCache.getDir().listFiles();
                    files = dir.listFiles();

                    Looper.prepare();
                    handler.sendEmptyMessage(1);
                    Looper.loop();
                } catch (Exception e) {
                    _log.inCatch(e.getMessage());
                }
            }
        }).start();
    }

    public void download(String downloadUrl) throws Exception {
//            downloadWithHttpURLConnection(downloadUrl);
            downloadWithOkHttpClient(downloadUrl);
    }

    private void downloadWithOkHttpClient(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(downloadUrl).build();
        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Failed to download file: " + response);
        }

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(response.body().bytes());

        fos.close();
    }


    private void downloadWithHttpURLConnection(String downloadUrl) throws IOException {
        HttpURLConnection httpCon;
        InputStream is;
        FileOutputStream fos;
        BufferedInputStream bis;
        BufferedOutputStream bos;

        URL downloadUrlCon = new URL(downloadUrl);
        httpCon = (HttpURLConnection) downloadUrlCon.openConnection();
        httpCon.setDoInput(true);

        is = httpCon.getInputStream();
        if (is == null) {
            throw new IOException("Failed to download file");
        }
        bis = new BufferedInputStream(is);
        fos = new FileOutputStream(file);
        bos = new BufferedOutputStream(fos);

        byte[] buffer = new byte[1024];
        int current;
        while ((current = bis.read(buffer, 0, buffer.length)) != -1) {
            bos.write(buffer, 0, current);
        }

        bos.close();
        bis.close();
        fos.close();
        is.close();
        httpCon.disconnect();
    }
}
