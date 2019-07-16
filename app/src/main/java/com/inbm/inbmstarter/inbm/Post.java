package com.inbm.inbmstarter.inbm;

import java.io.IOException;

abstract public class Post extends AbsWeb{
    String json = "";

    public Post(OnJsonLoadListener listener, String json) {
        super(listener);
        this.json = json;
    }

    @Override
    protected String getHTML(String url) throws IOException {
        return _web.post(url, json);
    }
}
