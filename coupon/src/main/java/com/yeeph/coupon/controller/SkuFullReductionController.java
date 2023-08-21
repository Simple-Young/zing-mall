package com.yeeph.coupon.controller;

import com.yeeph.coupon.entity.SkuFullReductionEntity;
import com.yeeph.coupon.service.SkuFullReductionService;
import com.yeeph.coupon.service.SkuLadderService;
import com.yeeph.common.to.SkuReductionTo;
import com.yeeph.common.utils.PageUtils;
import com.yeeph.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/skufullreduction")
public class SkuFullReductionController {
    @Autowired
    private SkuFullReductionService skuFullReductionService;

    @Autowired
    private SkuLadderService skuLadderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = skuFullReductionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        SkuFullReductionEntity skuFullReduction = skuFullReductionService.getById(id);

        return R.ok().put("skuFullReduction", skuFullReduction);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SkuFullReductionEntity skuFullReduction) {
        skuFullReductionService.save(skuFullReduction);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SkuFullReductionEntity skuFullReduction) {
        skuFullReductionService.updateById(skuFullReduction);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        skuFullReductionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @PostMapping("/saveInfo")
    public R saveSkuReductionTo(@RequestBody SkuReductionTo skuReductionTo) {
        skuFullReductionService.saveSkuReductionTo(skuReductionTo);
        return R.ok();
    }
}
