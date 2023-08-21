package com.yeeph.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeph.common.utils.PageUtils;
import com.yeeph.product.entity.AttrAttrgroupRelationEntity;
import com.yeeph.product.entity.AttrEntity;
import com.yeeph.product.entity.ProductAttrValueEntity;
import com.yeeph.product.vo.AttrRespVo;
import com.yeeph.product.vo.AttrVo;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author Ethan
 * @email hongshengmo@163.com
 * @date 2020-05-27 15:38:37
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params, long catelogId, String attrType);


    void saveAttr(AttrVo attr);

    AttrRespVo getAttrInfo(Long attrId);

    void updateAttr(AttrVo attr);

    List<AttrEntity> getRelationAttr(Long attrgroupId);

    PageUtils getNoRelationAttr(Long attrgroupId, Map<String, Object> params);

    void saveRelationBatch(List<AttrAttrgroupRelationEntity> attrGroupEntities);

    List<ProductAttrValueEntity> listAttrsforSpu(Long spuId);

    void updateSpuAttrs(Long spuId, List<ProductAttrValueEntity> attrValueEntities);

    List<Long> selectSearchAttrIds(List<Long> attrIds);
}

