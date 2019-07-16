package com.inbm.inbmstarter.inbm;

import java.io.IOException;

abstract public class GetWithHeaders extends AbsWeb{

    abstract protected  _web._header_[] getHeaders();
    protected String param = "";

    public GetWithHeaders(OnJsonLoadListener listener, String param) {
        super(listener);
        this.param = param;
    }

    @Override
    protected String getHTML(String url) throws IOException {
        String html = _web.getWithHeaders(url+param,getHeaders());
//        if (html.contains("false")) {
//            _log.e("false");
//        }
//        _log.e(html);
        return html;
//        return _web.getWithHeaders(url+param,getHeaders());
    }

}
