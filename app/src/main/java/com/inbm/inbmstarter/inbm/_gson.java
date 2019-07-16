package com.inbm.inbmstarter.inbm;

import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class _gson {

    public static class UriSerializer implements JsonSerializer<Uri> {
        public JsonElement serialize(Uri src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.toString());
        }
    }

    public static class UriDeserializer implements JsonDeserializer<Uri> {
        @Override
        public Uri deserialize(final JsonElement src, final Type srcType, final JsonDeserializationContext context) throws JsonParseException {
            return Uri.parse(src.getAsString());
        }
    }

    public static Gson getGson() {
        return new Gson();
    }

    public static Gson getGsonUriSerializer() {
        return new GsonBuilder().registerTypeAdapter(Uri.class, new UriSerializer()).create();
    }

    public static Gson getGsonUriDeserializer() {
        return new GsonBuilder().registerTypeAdapter(Uri.class, new UriDeserializer()).create();
    }
}
