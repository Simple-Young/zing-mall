package com.yeeph.auth.controller;

import com.yeeph.auth.feign.MemberFeignService;
import com.yeeph.auth.vo.UserRegisterVo;
import com.yeeph.common.constant.AuthServerConstant;
import com.yeeph.common.constant.RedisConstant;
import com.yeeph.common.utils.HashIdsUtils;
import com.yeeph.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Resource
    private MemberFeignService memberFeignService;

    @PostMapping("/phone")
    public R registerByPhone(@RequestBody @Validated UserRegisterVo registerVo){
        UserRegisterVo userRegisterVo = new UserRegisterVo();
        return memberFeignService.register(userRegisterVo);
    }

    @PostMapping("/email")
    public R registerByEmail(@Validated @RequestBody UserRegisterVo registerVo){
        //获取验证码
        String key = AuthServerConstant.EMAIL_CODE_CACHE_PREFIX + registerVo.getEmail();
        String code = redisTemplate.opsForValue().get(key);
        log.info(code);
        if(StringUtils.isEmpty(code))
            return R.error("验证码已过期");
        //判断提交验证码与获取验证码是否相同
        code = code.split("_")[0];
        if(!StringUtils.equals(code,registerVo.getCode())){
            return R.error("验证码不同");
        }
        //删除redis验证码
        redisTemplate.delete(key);
        //从redis中取userId
        String userId = redisTemplate.opsForSet().pop(RedisConstant.REDIS_USER_IDS_KEY);
        //redis 中不存在
        if(StringUtils.isEmpty(userId) || userId.length()<8){
            userId = HashIdsUtils.RandomOneUserId();
        }
        registerVo.setUserId(userId);
        //调用会员注册服务
        return memberFeignService.register(registerVo);
    }
}
