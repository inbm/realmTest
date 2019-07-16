package com.inbm.inbmstarter.inbm;

import org.json.JSONException;

public class TestPost extends PostWithHeaders {
    public TestPost(OnJsonLoadListener listener, String json) {
        super(listener, json);
    }

    @Override
    protected _web._header_[] getHeaders() {

        return new _web._header_[0];
    }

    @Override
    public <T> T parse(String html) throws JSONException {
        return null;
    }

    @Override
    protected String getURL() {
        return null;
    }
}
