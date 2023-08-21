package com.yeeph.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeph.common.utils.PageUtils;
import com.yeeph.product.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author Ethan
 * @email hongshengmo@163.com
 * @date 2020-05-27 15:38:37
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateCascade(BrandEntity brand);
}

