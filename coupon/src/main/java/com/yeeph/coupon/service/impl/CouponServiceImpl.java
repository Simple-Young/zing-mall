package com.yeeph.coupon.service.impl;

import com.yeeph.coupon.dao.CouponDao;
import com.yeeph.coupon.entity.CouponEntity;
import com.yeeph.coupon.service.CouponService;
import com.yeeph.common.utils.PageUtils;
import com.yeeph.common.utils.Query;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;



@Service("couponService")
public class CouponServiceImpl extends ServiceImpl<CouponDao, CouponEntity> implements CouponService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CouponEntity> page = this.page(
                new Query<CouponEntity>().getPage(params),
                new QueryWrapper<CouponEntity>()
        );

        return new PageUtils(page);
    }

}