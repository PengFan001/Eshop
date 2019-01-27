package com.miaoshaproject.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

public class ValidationResult {

    private boolean hasErrors = false;

    private HashMap<String, String> errorMsgMap = new HashMap<>();


    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public HashMap<String, String> getErrorMsgMap() {
        return errorMsgMap;
    }

    public void setErrorMsgMap(HashMap<String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }

    //实现通用的通过格式化字符串获取错误结果的MSG方法
    public String getErrMsg(){
        return StringUtils.join(errorMsgMap.values().toArray(),",");
    }

}
