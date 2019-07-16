package com.inbm.inbmstarter.inbm;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class _date {
    public static String getDateFormat(String timestampStr, String format) {
        long timestamp = Long.parseLong(timestampStr);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(Calendar.getInstance().getTimeZone());

        return dateFormat.format(new Date(timestamp));
    }

    public static String convertDateFormat(String dateStr, String originFormatStr, String targetFormatStr) {
        String targetDateStr = null;

        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat originFormat = new SimpleDateFormat(originFormatStr);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat targetFormat = new SimpleDateFormat(targetFormatStr);

            Date originDate = originFormat.parse(dateStr);

            targetDateStr = targetFormat.format(originDate);
        } catch (Exception e) {
            _log.e(e.getMessage());
            e.printStackTrace();
        }

        return targetDateStr;
    }
}
