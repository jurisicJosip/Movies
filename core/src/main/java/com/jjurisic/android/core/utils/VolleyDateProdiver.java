package com.jjurisic.android.core.utils;

import android.support.annotation.NonNull;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jurisicJosip.
 */
public class VolleyDateProdiver {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    private static final JsonSerializer<Date> dateSer = new JsonSerializer<Date>() {
        @Override
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
            if (src == null) {
                return null;
            }

            return new JsonPrimitive(formatter.format(src));
        }
    };

    private static final JsonDeserializer<Date> dateDeser = new JsonDeserializer<Date>() {
        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json == null) {
                return null;
            }

            try {
                return formatter.parse(json.getAsString());
            } catch (ParseException e) {
                return null;
            }
        }
    };

    @NonNull
    public static GsonBuilder newGsonInstance() {
        return new GsonBuilder().registerTypeAdapter(Date.class, dateSer).registerTypeAdapter(Date.class, dateDeser);
    }
}
