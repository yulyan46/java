package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * Created by iulian on 06.01.2017.
 */
public class JsonParser {
    public static String toJsonString(Object src){
        Gson gson = new GsonBuilder().create();
        return gson.toJson(src);
    }

    public static Object fromJsonString(String src, Type type){
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(src, type);
    }
}
