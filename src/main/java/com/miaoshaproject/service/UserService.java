package com.miaoshaproject.service;

import com.miaoshaproject.service.model.UserModel;

public interface UserService {

    //通过用户ID获取用户信息的方法
    UserModel getUserById(Integer id);

}
