package com.inbm.inbmstarter;

import com.inbm.inbmstarter.inbm.Get;
import com.inbm.inbmstarter.inbm._regex;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class GetFlower extends Get {
    public GetFlower(OnJsonLoadListener listener, String param) {
        super(listener, param);
    }

    @Override
    public <T> T parse(String html) throws JSONException, IOException {

        ArrayList<String> urls = _regex.images(html);
        ArrayList<Adapter4Flower._data_> datas = new ArrayList<>();

        urls.forEach( url -> datas.add(new Adapter4Flower._data_(url, url)));


        return (T) datas;
    }

    @Override
    protected String getURL() {
        return "https://www.google.com/search?q=flower&newwindow=1&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjvivmy067jAhWbxosBHetMAAMQ_AUIECgB&biw=1491&bih=867&dpr=2";
    }
}
