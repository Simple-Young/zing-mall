package com.yeeph.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeph.coupon.entity.CouponEntity;
import com.yeeph.common.utils.PageUtils;

import java.util.Map;


public interface CouponService extends IService<CouponEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

