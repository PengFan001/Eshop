package com.miaoshaproject.service;

import com.miaoshaproject.service.model.PromoModel;

public interface PromoService {

    //根据itemID获取即将进行或者是正在进行的活动
    PromoModel getPromoByItemId(Integer itemId);

}
