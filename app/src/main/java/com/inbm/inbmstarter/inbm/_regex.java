package com.inbm.inbmstarter.inbm;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by inbm on 2017. 3. 19..
 */

public class _regex {
    public static ArrayList<String> images(String data){

        String regex = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(data);

        ArrayList<String> arr = new ArrayList<>();

        while (m.find()) {

            arr.add(m.group(1));
            //_log.m("-->", m.group(1));

        }


        return arr;

    }
}
