package com.yeeph.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeph.common.utils.PageUtils;
import com.yeeph.product.entity.AttrGroupEntity;
import com.yeeph.product.vo.AttrGroupWithAttrVo;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author Ethan
 * @email hongshengmo@163.com
 * @date 2020-05-27 15:38:37
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {


    PageUtils queryPage(Map<String, Object> params, long catelogId);

    List<AttrGroupWithAttrVo> getAttrGroupWithAttrByCatelogId(Long catId);
}

