package com.yeeph.coupon.controller;

import com.yeeph.coupon.entity.CouponHistoryEntity;
import com.yeeph.coupon.service.CouponHistoryService;
import com.yeeph.common.utils.PageUtils;
import com.yeeph.common.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;


@RestController
@RequestMapping("/couponhistory")
public class CouponHistoryController {

    @Resource
    private CouponHistoryService couponHistoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = couponHistoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        CouponHistoryEntity couponHistory = couponHistoryService.getById(id);

        return R.ok().put("couponHistory", couponHistory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CouponHistoryEntity couponHistory) {
        couponHistoryService.save(couponHistory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CouponHistoryEntity couponHistory) {
        couponHistoryService.updateById(couponHistory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        couponHistoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
