package com.miaoshaproject.controller;

import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;
import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")
//用于处理使用jQuery和ajax进行数据请求时出现跨域的情况, 其中allowedHeaders是用于session共享的跨域请求
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    //用户获取短信验证码的接口
    //value、method、consumes字段用于前后端分离时，后端对前端数据请求时的识别
    @RequestMapping(value = "/getOtp", method = {RequestMethod.POST}, consumes = CONTENT_TYPE_FORMED)
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "telephone")String telephone){

        //生成随机的验证码
        Random random = new Random();
        Integer randomOtp = random.nextInt(99999);
        randomOtp = randomOtp + 10000;
        String randomOtpCode = String.valueOf(randomOtp);

        //将验证码和手机号关联起来，最好的方法采用Redis来做，此处采用HTTPSession方式来完成
        httpServletRequest.getSession().setAttribute(telephone, randomOtpCode);

        System.out.println("telephone=" + telephone + "\toptCode=" + randomOtpCode);

        return CommonReturnType.create(null);
    }

    //用户注册功能的实现
    @RequestMapping(value = "/register", method = {RequestMethod.POST}, consumes = CONTENT_TYPE_FORMED)
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telephone")String telephone,
                                     @RequestParam(name = "otpCode")String otpCode,
                                     @RequestParam(name = "name")String name,
                                     @RequestParam(name = "gender")Integer gender,
                                     @RequestParam(name = "age")Integer age,
                                     @RequestParam(name = "password")String password) throws BusinessException, NoSuchAlgorithmException {
        //验证手机和otp验证码是否符合
        String inSessionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(telephone);
        if(!com.alibaba.druid.util.StringUtils.equals(otpCode, inSessionOtpCode)){
            throw new BusinessException(EmBusinessError.ERROR_OTPCODE, "短信验证码不符合");
        }

        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setGender(gender);
        userModel.setAge(age);
        userModel.setTelephone(telephone);
        userModel.setEncrptPassword(this.EncodeByMD5(password));
        userModel.setRegisterMode("byPhone");
        userModel.setThirdPartyId("");

        userService.register(userModel);

        return CommonReturnType.create(null);
    }

    //用户登录接口的实现
    @RequestMapping(value = "/login", method = {RequestMethod.POST}, consumes = CONTENT_TYPE_FORMED)
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "telephone")String telephone,
                                  @RequestParam(name = "password")String password) throws BusinessException, NoSuchAlgorithmException {

        if (org.apache.commons.lang3.StringUtils.isEmpty(telephone) ||
                StringUtils.isEmpty(password)){
            throw new BusinessException(EmBusinessError.PARAMENTER_VAILDATION_ERROR);
        }

        UserModel userModel = userService.validateLogin(telephone, this.EncodeByMD5(password));

        //生成登录凭证，加入到用户成功登录的session中
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);

        return CommonReturnType.create(null);
    }

    //获取前端用于显示的用户信息
    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        //调用service服务并获取对应的ID的用户对象并返回给前端
        UserModel userModel = userService.getUserById(id);

        if(userModel == null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        //将核心领域模型用户对象转化为可供UI使用的viewobject
        UserVO userVO = convertFromModel(userModel);

        //返回通用数据
        return CommonReturnType.create(userVO);

    }

    //后端用户数据模型转换为前端用户数据的显示模型
    private UserVO convertFromModel(UserModel userModel){
        if(userModel == null){
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);

        return userVO;
    }

    //对用户注册时输入的密码进行MD5方式的加密
    public String EncodeByMD5(String password) throws NoSuchAlgorithmException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Decoder = new BASE64Encoder();

        //加密字符串
        String encrptPassword = base64Decoder.encode(md5.digest(password.getBytes()));

        return encrptPassword;
    }

}
