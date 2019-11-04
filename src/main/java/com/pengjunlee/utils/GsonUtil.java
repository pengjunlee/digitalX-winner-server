package com.pengjunlee.utils;

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

/**
 * @author pengjunlee
 * @create 2019-09-10 18:10
 */



public class GsonUtil
{

    /**
     * 获取Gson对象
     *
     * @return
     */
    public static Gson getGson()
    {
        return new GsonBuilder().serializeNulls().registerTypeAdapter(Integer.class, new IntegerAdapter())
                .registerTypeAdapter(int.class, new IntegerAdapter())
                .registerTypeAdapter(Double.class, new DoubleAdapter())
                .registerTypeAdapter(double.class, new DoubleAdapter())
                .registerTypeAdapter(Long.class, new LongAdapter()).registerTypeAdapter(long.class, new LongAdapter())
                .create();
    }

    public static class IntegerAdapter implements JsonSerializer<Integer>, JsonDeserializer<Integer>
    {
        @Override
        public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException
        {
            try
            {
                if (json.getAsString().equals("") || json.getAsString().equals("null"))
                {// 定义为int类型,如果后台返回""或者null,则返回0
                    return 0;
                }
            }
            catch (Exception ignore)
            {
                return 0;
            }

            try
            {
                return json.getAsInt();
            }
            catch (NumberFormatException e)
            {
                return 0;
            }
        }

        @Override
        public JsonElement serialize(Integer src, Type typeOfSrc, JsonSerializationContext context)
        {
            return new JsonPrimitive(src);
        }
    }

    public static class DoubleAdapter implements JsonSerializer<Double>, JsonDeserializer<Double>
    {
        @Override
        public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException
        {
            try
            {
                if (json.getAsString().equals("") || json.getAsString().equals("null"))
                {// 定义为int类型,如果后台返回""或者null,则返回0
                    return 0.00;
                }
            }
            catch (Exception ignore)
            {
                return 0.00;
            }

            try
            {
                return json.getAsDouble();
            }
            catch (NumberFormatException e)
            {
                return 0.00;
            }
        }

        @Override
        public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context)
        {
            return new JsonPrimitive(src);
        }
    }

    public static class LongAdapter implements JsonSerializer<Long>, JsonDeserializer<Long>
    {
        @Override
        public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException
        {
            try
            {
                if (json.getAsString().equals("") || json.getAsString().equals("null"))
                {// 定义为int类型,如果后台返回""或者null,则返回0
                    return 0l;
                }
            }
            catch (Exception ignore)
            {
                return 0l;
            }
            try
            {
                return json.getAsLong();
            }
            catch (NumberFormatException e)
            {
                return 0l;
            }
        }

        @Override
        public JsonElement serialize(Long src, Type typeOfSrc, JsonSerializationContext context)
        {
            return new JsonPrimitive(src);
        }
    }
}
