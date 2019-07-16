package com.inbm.inbmstarter.inbm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by inbm on 2017. 2. 22..
 */

public class _network {
    public static String readWeb(String url){
        String ret = "";


        String line = "";

        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(new URL(url).openStream(), "euc-kr"));
            while(null != (line = br.readLine())){
                ret += line;

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;

    }
}
