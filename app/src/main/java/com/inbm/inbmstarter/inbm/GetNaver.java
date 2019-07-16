package com.inbm.inbmstarter.inbm;

import org.json.JSONException;

import java.io.IOException;

public class GetNaver extends AbsWeb4Progess {
    public GetNaver(OnJsonLoadListener listener) {
        super(listener);
    }

    @Override
    public <T> T parse(String html) throws JSONException, IOException {
        return null;
    }

    @Override
    protected String getHTML(String url) throws IOException {
        return null;
    }

    @Override
    protected String getURL() {
        return null;
    }
}