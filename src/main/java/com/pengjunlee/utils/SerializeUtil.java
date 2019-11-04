package com.pengjunlee.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author pengjunlee
 * @create 2019-09-10 18:26
 */
public class SerializeUtil {
    private static Logger logger = LoggerFactory.getLogger(SerializeUtil.class);

    /**
     * 将对象转换成二进制
     * @param object
     * @return
     * @throws
     */
    public static byte[] serialize(Object object)
    {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        byte[] bytes = null;
        try
        {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            bytes = baos.toByteArray();
            oos.flush();
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
        finally
        {
            if (null != oos)
            {
                try
                {
                    oos.close();
                }
                catch (IOException e)
                {
                    logger.error(e.getMessage(), e);
                }
            }
            if (null != baos)
            {
                try
                {
                    baos.close();
                }
                catch (IOException e)
                {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return bytes;
    }

    /**
     * 二进制转换成对象
     * @param bytes
     * @return
     * @throws
     */
    public static Object unserialize(byte[] bytes)
    {

        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        Object object = null;
        try
        {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            object = ois.readObject();
            return object;
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
        finally
        {
            if (null != ois)
            {
                try
                {
                    ois.close();
                }
                catch (IOException e)
                {
                    logger.error(e.getMessage(), e);
                }
            }
            if (null != bais)
            {
                try
                {
                    bais.close();
                }
                catch (IOException e)
                {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return object;
    }

}
