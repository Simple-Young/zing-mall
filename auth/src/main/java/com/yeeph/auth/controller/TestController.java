package com.yeeph.auth.controller;

import com.yeeph.auth.feign.MemberFeignService;
import com.yeeph.common.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private MemberFeignService memberFeignService;



    @GetMapping("/user/{id}")
    public R test(@PathVariable Long id){
        return memberFeignService.info(id);
    }

    @GetMapping("/lisi/{id}")
    public R test2(@PathVariable Long id){
        return R.ok("lisi");
    }


}
