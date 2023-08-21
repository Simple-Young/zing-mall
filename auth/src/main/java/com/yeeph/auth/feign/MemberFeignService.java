package com.yeeph.auth.feign;

import com.yeeph.auth.feign.fallback.MemberFallbackService;
import com.yeeph.auth.vo.SocialUser;
import com.yeeph.auth.vo.UserLoginVo;
import com.yeeph.auth.vo.UserRegisterVo;
import com.yeeph.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "member", path = "member", fallback = MemberFallbackService.class)
public interface MemberFeignService {

    @RequestMapping("/member/register2")
    R register2(@RequestBody UserRegisterVo registerVo);

    @RequestMapping("/member/register")
    R register(@RequestBody UserRegisterVo registerVo);

    @RequestMapping("/member/login")
    R login(@RequestBody UserLoginVo loginVo);

    @RequestMapping("/member/oauth2/login")
    R login(@RequestBody SocialUser socialUser);

    @RequestMapping("/growthchangehistory/info/{id}")
    R info(@PathVariable("id") Long id);
}
