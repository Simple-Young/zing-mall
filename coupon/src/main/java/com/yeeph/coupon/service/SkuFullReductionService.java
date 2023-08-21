package com.yeeph.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeph.coupon.entity.SkuFullReductionEntity;
import com.yeeph.common.to.SkuReductionTo;
import com.yeeph.common.utils.PageUtils;

import java.util.Map;

public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSkuReductionTo(SkuReductionTo skuReductionTo);
}

