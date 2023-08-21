package com.yeeph.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeeph.common.utils.PageUtils;
import com.yeeph.common.utils.Query;
import com.yeeph.product.dao.SkuSaleAttrValueDao;
import com.yeeph.product.entity.SkuSaleAttrValueEntity;
import com.yeeph.product.service.SkuSaleAttrValueService;
import com.yeeph.product.vo.SkuItemSaleAttrVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("skuSaleAttrValueService")
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<SkuSaleAttrValueDao, SkuSaleAttrValueEntity> implements SkuSaleAttrValueService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuSaleAttrValueEntity> page = this.page(
                new Query<SkuSaleAttrValueEntity>().getPage(params),
                new QueryWrapper<SkuSaleAttrValueEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<SkuItemSaleAttrVo> listSaleAttrs(Long spuId) {
        return baseMapper.listSaleAttrs(spuId);
    }

    @Override
    public List<String> getSkuSaleAttrValuesAsString(Long skuId) {
        return baseMapper.getSkuSaleAttrValuesAsString(skuId);
    }

}