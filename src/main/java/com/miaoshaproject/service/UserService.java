package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.UserModel;

public interface UserService {

    //通过用户ID获取用户信息的方法
    UserModel getUserById(Integer id);

    void register(UserModel userModel) throws BusinessException;

    /**
     *
     * @param telephone:用户注册时所用的手机号
     * @param encrptPassword：数据库中存放的加密过后的密码
     * @throws BusinessException
     */
    UserModel validateLogin(String telephone, String encrptPassword) throws BusinessException;

}
