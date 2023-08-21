package com.yeeph.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeph.coupon.entity.CouponHistoryEntity;
import com.yeeph.common.utils.PageUtils;


import java.util.Map;


public interface CouponHistoryService extends IService<CouponHistoryEntity>{

    PageUtils queryPage(Map<String, Object> params);
}

