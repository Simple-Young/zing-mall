package com.yeeph.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeph.common.utils.PageUtils;
import com.yeeph.product.entity.AttrAttrgroupRelationEntity;

import java.util.Map;

/**
 * 属性&属性分组关联
 *
 * @author Ethan
 * @email hongshengmo@163.com
 * @date 2020-05-27 15:38:37
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

