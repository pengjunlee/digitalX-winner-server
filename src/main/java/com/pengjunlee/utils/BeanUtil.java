package com.pengjunlee.utils;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import com.google.gson.Gson;

/**
 * @author pengjunlee
 * @create 2019-09-10 18:17
 */
public class BeanUtil {

    private static Mapper dozerMapper = new DozerBeanMapper();

    /**
     * 将一个对象的内容，深拷贝到一个新对象（如果仅仅是浅拷贝时，请使用BeanUtils.copyProperties方法，性能会更好）
     *
     * @param sourceObject     源对象
     * @param destinationClass 目标对象的Class
     * @return 拷贝好的目标对象
     * @throws
     */
    public static <T> T deepCopy(Object sourceObject, Class<T> destinationClass) {
        return dozerMapper.map(sourceObject, destinationClass);
    }

    /**
     * 克隆一个对象
     * 原理：基于Gson工具，先转字符串，然后转回成对象
     *
     * @param sourceObject     源对象
     * @param destinationClass 目标对象的Class
     * @return 拷贝好的目标对象
     * @throws
     */
    public static <T> T clone(Object sourceObject, Class<T> destinationClass) {
        Gson gson = GsonUtil.getGson();
        String jsonStr = gson.toJson(sourceObject);
        return gson.fromJson(jsonStr, destinationClass);
    }

    /**
     * 将一个对象的内容，深拷贝到一个新对象（如果仅仅是浅拷贝时，请使用BeanUtils.copyProperties方法，性能会更好）
     *
     * @param sourceObject      源对象
     * @param destinationObject 目标对象
     * @throws
     */
    public static void deepCopy(Object sourceObject, Object destinationObject) {
        dozerMapper.map(sourceObject, destinationObject);
    }
}
