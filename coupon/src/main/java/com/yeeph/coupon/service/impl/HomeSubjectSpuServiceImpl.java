package com.yeeph.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeeph.coupon.dao.HomeSubjectSpuDao;
import com.yeeph.coupon.entity.HomeSubjectSpuEntity;
import com.yeeph.coupon.service.HomeSubjectSpuService;
import com.yeeph.common.utils.PageUtils;
import com.yeeph.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("homeSubjectSpuService")
public class HomeSubjectSpuServiceImpl extends ServiceImpl<HomeSubjectSpuDao, HomeSubjectSpuEntity> implements HomeSubjectSpuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HomeSubjectSpuEntity> page = this.page(
                new Query<HomeSubjectSpuEntity>().getPage(params),
                new QueryWrapper<HomeSubjectSpuEntity>()
        );

        return new PageUtils(page);
    }

}