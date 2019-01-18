package com.miaoshaproject.error;

public enum EmBusinessError implements CommonError {

    //10000开头表示通用的错误类型
    PARAMENTER_VAILDATION_ERROR(10001, "参数输入不合法"),

    UNKNOWED_ERROR(10002, "未知错误"),

    //20000开头表示用户信息相关的错误
    USER_NOT_EXIST(20001, "用户不存在")

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
