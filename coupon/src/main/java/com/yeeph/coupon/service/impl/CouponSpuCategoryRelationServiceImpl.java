package com.yeeph.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeeph.coupon.dao.CouponSpuCategoryRelationDao;
import com.yeeph.coupon.entity.CouponSpuCategoryRelationEntity;
import com.yeeph.coupon.service.CouponSpuCategoryRelationService;
import com.yeeph.common.utils.PageUtils;
import com.yeeph.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("couponSpuCategoryRelationService")
public class CouponSpuCategoryRelationServiceImpl extends ServiceImpl<CouponSpuCategoryRelationDao, CouponSpuCategoryRelationEntity> implements CouponSpuCategoryRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CouponSpuCategoryRelationEntity> page = this.page(
                new Query<CouponSpuCategoryRelationEntity>().getPage(params),
                new QueryWrapper<CouponSpuCategoryRelationEntity>()
        );

        return new PageUtils(page);
    }

}