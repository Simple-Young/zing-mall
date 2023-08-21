package com.yeeph.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeph.coupon.entity.SeckillSessionEntity;
import com.yeeph.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface SeckillSessionService extends IService<SeckillSessionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SeckillSessionEntity> getSeckillSessionsIn3Days();
}

