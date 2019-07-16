package com.inbm.inbmstarter;

import com.inbm.inbmstarter.inbm.Get;

import org.json.JSONException;

import java.io.IOException;

public class GetMountain<T> extends Get {
    public GetMountain(OnJsonLoadListener listener, String param) {
        super(listener, param);
    }

    @Override
    public  T parse(String html) throws JSONException, IOException {
        return null;
    }

    @Override
    protected String getURL() {
        return null;
    }
}
