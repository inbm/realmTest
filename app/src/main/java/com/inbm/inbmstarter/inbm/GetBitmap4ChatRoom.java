package com.inbm.inbmstarter.inbm;

import android.content.Context;
import android.graphics.Bitmap;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class GetBitmap4ChatRoom extends AbsBitmapMerge {
    ArrayList<String> urls;
    public GetBitmap4ChatRoom(OnJsonLoadListener listener, Context context, ArrayList<String> urls) {
        super(listener, context);
        this.urls = urls;
    }

    @Override
    public <T> T parse(Bitmap bitmap) throws JSONException, IOException {
        return (T) bitmap;
    }

    @Override
    protected ArrayList<String> getURLS() {
        return urls;
    }
}