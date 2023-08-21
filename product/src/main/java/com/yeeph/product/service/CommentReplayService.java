package com.yeeph.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeph.common.utils.PageUtils;
import com.yeeph.product.entity.CommentReplayEntity;

import java.util.Map;

/**
 * 商品评价回复关系
 *
 * @author Ethan
 * @email hongshengmo@163.com
 * @date 2020-05-27 15:38:36
 */
public interface CommentReplayService extends IService<CommentReplayEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

