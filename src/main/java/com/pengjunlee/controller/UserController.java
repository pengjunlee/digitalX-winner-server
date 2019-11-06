package com.pengjunlee.controller;


import com.pengjunlee.domain.BaseResponse;
import com.pengjunlee.domain.UserAuthInfo;
import com.pengjunlee.domain.UserEntity;
import com.pengjunlee.service.UserService;
import com.pengjunlee.utils.PageUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 9:27
 */
@RequestMapping("/api/v1/user")
@RestController
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/info")
    public Object getUserInfo(@RequestParam(name = "name") String name) {
        BaseResponse<Object> ret = new BaseResponse<Object>();
        UserAuthInfo userAuth = userService.getUserAuthByName(name);
        ret.setCode(0);
        ret.setMessage("获取用户数据成功");
        if (userAuth != null) {
            ret.setData(userAuth);
        }
        return ret;
    }


    @PostMapping("/create")
    @RequiresRoles(value = {"admin"})
    public Object createUser(@RequestBody UserEntity userEntity) {
        // 设置默认字段
        String password = new SimpleHash("SHA-1", "123456", userEntity.getName(), 16).toString();
        userEntity.setPassword(password);
        userEntity.setLocked(false);
        boolean save = userService.save(userEntity);

        BaseResponse<Object> ret = new BaseResponse<Object>();
        if (save) {
            ret.setCode(0);
            ret.setMessage("添加用户成功");
            ret.setData(userEntity);
        } else {
            ret.setCode(-1);
            ret.setMessage("添加用户异常");
        }

        return ret;
    }

    @PutMapping("/update")
    public Object updateUser(@RequestBody UserEntity userEntity) {
        boolean b = false;
        if (userEntity.getId() != null) {
            b = userService.updateById(userEntity);
        }
        BaseResponse<Object> ret = new BaseResponse<Object>();
        if (b) {
            ret.setCode(0);
            ret.setMessage("更新用户成功");
        } else {
            ret.setCode(-1);
            ret.setMessage("更新用户异常");
        }
        return ret;
    }

    @PutMapping("/password/update")
    public Object updatePassword(@RequestBody UserEntity userEntity) {
        boolean b = true;
        if (userEntity.getName() != null && userEntity.getPassword() != null) {
            String password = new SimpleHash("SHA-1", userEntity.getPassword(), userEntity.getName(), 16).toString();
            userEntity.setPassword(password);
            b = userService.updateByName(userEntity);
        }
        BaseResponse<Object> ret = new BaseResponse<Object>();
        if (b) {
            ret.setCode(0);
            ret.setMessage("密码更新成功");
        } else {
            ret.setCode(-1);
            ret.setMessage("密码更新失败");
        }
        return ret;
    }

    @GetMapping("/list")
    public Object userList(@RequestParam Map<String, Object> params) {
        try {
            PageUtil pageUtil = userService.pageUserByCond(params);
            BaseResponse<Object> ret = new BaseResponse<Object>(pageUtil);
            ret.setCode(0);
            ret.setMessage("用户数据加载成功");
            return ret;
        } catch (Exception e) {
            BaseResponse<Object> ret = new BaseResponse<Object>();
            ret.setCode(-1);
            ret.setMessage("获取数据失败");
            return ret;
        }

    }

    @DeleteMapping("/delete/{id}")
    @RequiresRoles(value = {"admin"})
    public Object userList(@PathVariable(name = "id") Long id) {
        BaseResponse<Object> ret = new BaseResponse<Object>();
        boolean b = userService.removeById(id);
        if (b) {
            ret.setCode(0);
            ret.setMessage("用户数据加载成功");

        } else {
            ret.setCode(-1);
            ret.setMessage("获取数据失败");
        }
        return ret;

    }
}