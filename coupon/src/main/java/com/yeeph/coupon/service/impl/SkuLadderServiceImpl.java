package com.yeeph.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeeph.coupon.dao.SkuLadderDao;
import com.yeeph.coupon.entity.SkuLadderEntity;
import com.yeeph.coupon.service.SkuLadderService;
import com.yeeph.common.utils.PageUtils;
import com.yeeph.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("skuLadderService")
public class SkuLadderServiceImpl extends ServiceImpl<SkuLadderDao, SkuLadderEntity> implements SkuLadderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuLadderEntity> page = this.page(
                new Query<SkuLadderEntity>().getPage(params),
                new QueryWrapper<SkuLadderEntity>()
        );

        return new PageUtils(page);
    }

}