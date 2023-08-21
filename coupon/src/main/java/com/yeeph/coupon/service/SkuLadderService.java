package com.yeeph.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeph.coupon.entity.SkuLadderEntity;
import com.yeeph.common.utils.PageUtils;

import java.util.Map;


public interface SkuLadderService extends IService<SkuLadderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

