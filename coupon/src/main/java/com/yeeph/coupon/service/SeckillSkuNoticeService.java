package com.yeeph.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeph.coupon.entity.SeckillSkuNoticeEntity;
import com.yeeph.common.utils.PageUtils;

import java.util.Map;


public interface SeckillSkuNoticeService extends IService<SeckillSkuNoticeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

