package com.miaoshaproject.service.model;


import java.math.BigDecimal;

//用户下单的交易模型
public class OrderModel {

    //订单号
    private String id;

    //购买的用户信息
    private Integer userId;

    //购买商品的id
    private Integer itemId;

    //购买商品的数量
    private Integer amount;

    //购买商品的单价, 若promoId非空，表示的是秒杀活动的价格
    private BigDecimal itemPrice;

    //购买的总金额, 若promoId非空，表示的是秒杀活动的总价格
    private BigDecimal orderPrice;

    //若promoId非空就表示按照秒杀的价格进行下单
    private Integer promoId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }
}
