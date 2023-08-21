package com.yeeph.coupon.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeeph.coupon.entity.SeckillSessionEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 秒杀活动场次
 *
 * @author Ethan
 * @email hongshengmo@163.com
 * @date 2020-05-27 20:03:33
 */
@Mapper
public interface SeckillSessionDao extends BaseMapper<SeckillSessionEntity> {

}
