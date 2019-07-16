package com.inbm.inbmstarter.inbm;

import java.io.IOException;

abstract public class PostWithHeaders extends AbsWeb{


    String json = "";
    abstract protected  _web._header_[] getHeaders();

    public PostWithHeaders(OnJsonLoadListener listener, String json) {
        super(listener);
        this.json = json;
    }

    @Override
    protected String getHTML(String url) throws IOException {
        return _web.postWithHeaders(url, json,getHeaders());
    }

}
