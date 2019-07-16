package com.inbm.inbmstarter.inbm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class _json {
	public static JSONObject json(String json_string){
		JSONObject j = null;
		try {
			j = new JSONObject(json_string);
		} catch (JSONException e) {
			_log.inCatch("파싱에러 : " + e.getMessage());
		}
		
		return j;
	}
	
	public static JSONObject array2object(JSONArray arr, int i){
		JSONObject o = null;
		try {
			o = ((JSONObject) arr.get(i));
		} catch (JSONException e) {
			_log.inCatch("파싱에러 : " + e.getMessage());
		}
		return o;
	}
	
	public static JSONArray arrayFromObject(JSONObject o, String key){
		
		JSONArray arr = null;
		try {
			arr = o.getJSONArray(key);
		} catch (JSONException e) {
			_log.inCatch("파싱에러 : " + e.getMessage());
		}
		return arr;
	}
}


