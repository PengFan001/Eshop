package com.miaoshaproject.error;

public enum EmBusinessError implements CommonError {

    //10000开头表示通用的错误类型
    PARAMENTER_VAILDATION_ERROR(10001, "参数输入不合法"),

    UNKNOWED_ERROR(10002, "未知错误"),

    ERROR_OTPCODE(10003, "验证码不正确"),

    //20000开头表示用户信息相关的错误
    USER_NOT_EXIST(20001, "用户不存在"),

    ERROR_USERNAME_OR_PASSWORD(20002,"用户名或密码错误"),

    USER_NOT_LOGIN(20003, "用户还未登录"),

    //30000开头表示交易中出现的错误
    STOCK_NOT_ENOUGH(30001, "库存不足"),

    ;

    private int errCode;
    private String errMsg;

    private EmBusinessError(int errCode, String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
