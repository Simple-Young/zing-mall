package com.yeeph.coupon.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeeph.coupon.dao.SkuFullReductionDao;
import com.yeeph.coupon.entity.SkuFullReductionEntity;
import com.yeeph.coupon.entity.SkuLadderEntity;
import com.yeeph.coupon.service.SkuFullReductionService;
import com.yeeph.coupon.service.SkuLadderService;
import com.yeeph.common.to.SkuReductionTo;
import com.yeeph.common.utils.PageUtils;
import com.yeeph.common.utils.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionDao, SkuFullReductionEntity> implements SkuFullReductionService {

    @Autowired
    private SkuLadderService skuLadderService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuFullReductionEntity> page = this.page(
                new Query<SkuFullReductionEntity>().getPage(params),
                new QueryWrapper<SkuFullReductionEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSkuReductionTo(SkuReductionTo skuReductionTo) {
        SkuFullReductionEntity skuFullReductionEntity = new SkuFullReductionEntity();
        BeanUtils.copyProperties(skuReductionTo, skuFullReductionEntity);
        this.baseMapper.insert(skuFullReductionEntity);
        SkuLadderEntity skuLadderEntity = new SkuLadderEntity();
        BeanUtils.copyProperties(skuReductionTo, skuLadderEntity);
        skuLadderService.save(skuLadderEntity);
    }

}