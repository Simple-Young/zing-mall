package com.yeeph.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeph.coupon.entity.HomeSubjectEntity;
import com.yeeph.common.utils.PageUtils;

import java.util.Map;


public interface HomeSubjectService extends IService<HomeSubjectEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

