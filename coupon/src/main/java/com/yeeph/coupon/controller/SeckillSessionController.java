package com.yeeph.coupon.controller;

import com.yeeph.coupon.entity.SeckillSessionEntity;
import com.yeeph.coupon.service.SeckillSessionService;
import com.yeeph.common.utils.PageUtils;
import com.yeeph.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/seckillsession")
public class SeckillSessionController {
    @Autowired
    private SeckillSessionService seckillSessionService;


    @RequestMapping("/getSeckillSessionsIn3Days")
    public R getSeckillSessionsIn3Days() {
        List<SeckillSessionEntity> seckillSessionEntities = seckillSessionService.getSeckillSessionsIn3Days();
        return R.ok().setData(seckillSessionEntities);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = seckillSessionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        SeckillSessionEntity seckillSession = seckillSessionService.getById(id);

        return R.ok().put("seckillSession", seckillSession);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SeckillSessionEntity seckillSession) {
        seckillSessionService.save(seckillSession);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SeckillSessionEntity seckillSession) {
        seckillSessionService.updateById(seckillSession);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        seckillSessionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
