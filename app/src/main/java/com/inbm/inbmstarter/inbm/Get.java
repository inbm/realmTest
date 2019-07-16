package com.inbm.inbmstarter.inbm;

import java.io.IOException;

abstract public class Get extends AbsWeb {

    protected String param = "";

    public Get(OnJsonLoadListener listener, String param) {
        super(listener);
        this.param = param;

    }

    @Override
    protected String getHTML(String url) throws IOException {
        String full_url = url + "?" + param;
        _log.m("full_url", full_url);
        return _web.get(full_url);
    }

}
