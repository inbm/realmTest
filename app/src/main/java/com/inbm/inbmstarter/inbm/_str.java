package com.inbm.inbmstarter.inbm;

/**
 * Created by inbm on 2017. 3. 24..
 */

public class _str {

    public static String[] splitByNumber(String s, int size) {
        if(s == null || size <= 0)
            return null;
        int chunks = s.length() / size + ((s.length() % size > 0) ? 1 : 0);
        String[] arr = new String[chunks];
        for(int i = 0, j = 0, l = s.length(); i < l; i += size, j++)
            arr[j] = s.substring(i, Math.min(l, i + size));
        return arr;
    }
}
