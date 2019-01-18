package com.miaoshaproject.response;

/**
 * 此类用来设置统一的返回状态和返回数据
 */

public class CommonReturnType {

    //表明对应请求的处理结果 有： success， fail
    private String status;

    //若status返回的是data，
    //若status返回fail则返回通用的错误码格式
    private Object data;

    //定义一个通用的创建方法
    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result, "success");      //result代表传入的数据，而success代表默认的状态代表成功
    }

    public static CommonReturnType create(Object result, String status){

        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setStatus(status);
        commonReturnType.setData(result);

        return commonReturnType;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
