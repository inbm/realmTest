package com.inbm.inbmstarter.inbm;

import java.io.IOException;

abstract public class DeleteWithHeaders extends AbsWeb{


    String json = "";
    abstract protected  _web._header_[] getHeaders();

    public DeleteWithHeaders(OnJsonLoadListener listener, String json) {
        super(listener);
        this.json = json;
    }

    @Override
    protected String getHTML(String url) throws IOException {
//        ArrayList<_header_> headers = new ArrayList<>();

        return _web.deleteWithHeaders(url, json, getHeaders());
    }

}
