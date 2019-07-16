package com.inbm.inbmstarter.inbm;


import android.content.SharedPreferences;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class _shared {
//    static private ObjectMapper mapper = new ObjectMapper();
//    static private Type friend_type = new TypeToken<ArrayList<_friend_>>() {
//    }.getType();
//    static private Shared<_friend_> sp_friend = new Shared<>(friend_type);
//    public static Shared db4_friend_() {
//        return sp_friend;
//    }
//
//
//    /**
//     *
//     *
//     * @param key
//     * @param value
//     * @return
//     */
//    public static _friend_ findOneFriend(String key, String value) {
//        try {
//            JsonNode jsonNode = mapper.readTree(sp_friend.json());
//            for (JsonNode jn : jsonNode) {
//                if (jn.get(key).asText().equals(value)) {
//                    _friend_ f = mapper.readValue(jn.toString(), _friend_.class);
//                    return f;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static void insertOneFriend(_friend_ friend) {
//        sp_friend.insertOne(friend);
//    }
//    public static ArrayList<_friend_> getAllFriend() {
//        ArrayList<_friend_> friends = (ArrayList<_friend_>) sp_friend.find();
//        if (friends == null) {
//            friends = new ArrayList<>();
//        }
//        return friends;
//    }
//    /**
//     * 업데이트를 하려면 어떤 친구인지 판별하여 그 친구값을 변경후 저장.
//     */
//    public static void updateFriend(String findKey, String findValue, String setKey, String setValue) {
//        JsonNode jsonNode = null;
//        try {
//            jsonNode = mapper.readTree(sp_friend.json());
//            for (JsonNode jn : jsonNode) {
//                if (jn.get(findKey).asText().equals(findValue)) {
//                    jn.path(setKey).toString();
//                    ((ObjectNode) jn).put(setKey, setValue);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    public static String getUUID() {
        SharedPreferences pref = App.getStaticContext().getSharedPreferences("inbmstarter", MODE_PRIVATE);
        return pref.getString("uuid", "");
    }

    public static void setUUID(String uuid) {
        SharedPreferences pref = App.getStaticContext().getSharedPreferences("inbmstarter", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("uuid", uuid);
        editor.apply();
    }

    /**
     * logIn 용 json
     *
     * @param u
     */

    public static void setLoginInfo(String u) {
        SharedPreferences pref = App.getStaticContext().getSharedPreferences("inbmstarter", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        _log.e(u);
        editor.putString("login_info", u);
        editor.apply();
    }

    public static String getLoginInfo() {
        SharedPreferences pref = App.getStaticContext().getSharedPreferences("inbmstarter", MODE_PRIVATE);
        return pref.getString("login_info", "");
    }

    public static void setMyProfile(String myProfile) {
        SharedPreferences pref = App.getStaticContext().getSharedPreferences("inbmstarter", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("myprofile", myProfile);
        editor.apply();
    }

    public static String getMyProfile() {
        SharedPreferences pref = App.getStaticContext().getSharedPreferences("inbmstarter", MODE_PRIVATE);
        return pref.getString("myprofile", "");
    }


    //Bearer Token
    public static void setBearerToken(String u) {
        SharedPreferences pref = App.getStaticContext().getSharedPreferences("inbmstarter", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("bearer_token", "Bearer " + u);
        editor.apply();
    }

    public static String getBearerToken() {
        SharedPreferences pref = App.getStaticContext().getSharedPreferences("inbmstarter", MODE_PRIVATE);
        return pref.getString("bearer_token", "");
    }

    public static void setDeviceToken(String u) {
        SharedPreferences pref = App.getStaticContext().getSharedPreferences("inbmstarter", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("device_token", u);
        editor.apply();
    }

    public static String getDeviceToken() {
        SharedPreferences pref = App.getStaticContext().getSharedPreferences("inbmstarter", MODE_PRIVATE);
        return pref.getString("device_token", "");
    }

    public static void setUserId(String u) {
        SharedPreferences pref = App.getStaticContext().getSharedPreferences("inbmstarter", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("user_id", u);
        editor.apply();
    }

    public static String getUserId() {
        SharedPreferences pref = App.getStaticContext().getSharedPreferences("inbmstarter", MODE_PRIVATE);
        return pref.getString("user_id", "");
    }

    public static void setPhoneNum(String s) {
        SharedPreferences pref = App.getStaticContext().getSharedPreferences("inbmstarter", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("phone_num", s);
        editor.apply();
    }

    public static String getPhoneNum() {
        SharedPreferences pref = App.getStaticContext().getSharedPreferences("inbmstarter", MODE_PRIVATE);
        return pref.getString("phone_num", "");
    }

    //주소록 동기화 timeStamp

    public static void setSyncedTimeToken(String u) {
        SharedPreferences pref = App.getStaticContext().getSharedPreferences("inbmstarter", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("sync_millisec", u);
        editor.apply();
    }

    public static String getSyncedTimeToken() {
        SharedPreferences pref = App.getStaticContext().getSharedPreferences("inbmstarter", MODE_PRIVATE);
        return pref.getString("sync_millisec", "");
    }

    //광고 오늘만 보기
    public static void setStopWatchingToday(String u) {
        SharedPreferences pref = App.getStaticContext().getSharedPreferences("inbmstarter", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("stop_watching", u);
        editor.apply();
    }

    public static String getStopWatchingToday() {
        SharedPreferences pref = App.getStaticContext().getSharedPreferences("inbmstarter", MODE_PRIVATE);
        return pref.getString("stop_watching", "false");
    }


}