package com.yeeph.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeeph.coupon.dao.MemberPriceDao;
import com.yeeph.coupon.entity.MemberPriceEntity;
import com.yeeph.coupon.service.MemberPriceService;
import com.yeeph.common.utils.PageUtils;
import com.yeeph.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("memberPriceService")
public class MemberPriceServiceImpl extends ServiceImpl<MemberPriceDao, MemberPriceEntity> implements MemberPriceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberPriceEntity> page = this.page(
                new Query<MemberPriceEntity>().getPage(params),
                new QueryWrapper<MemberPriceEntity>()
        );

        return new PageUtils(page);
    }

}