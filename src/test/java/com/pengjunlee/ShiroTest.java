package com.pengjunlee;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

/**
 * @author pengjunlee
 * @create 2019-10-31 15:44
 */
public class ShiroTest {

    @Test
    public void test1(){
        //加密密码
        String password = new SimpleHash("SHA-1", "123456", "admin", 16).toString();
        System.out.println(password);
    }
}
