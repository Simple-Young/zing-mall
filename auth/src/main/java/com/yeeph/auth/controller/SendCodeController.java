package com.yeeph.auth.controller;


import com.yeeph.auth.feign.ThirdPartFeignService;
import com.yeeph.auth.service.IMailService;
import com.yeeph.auth.vo.CodeVo;
import com.yeeph.common.constant.AuthServerConstant;
import com.yeeph.common.exception.BizCodeEnum;
import com.yeeph.common.utils.R;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/code")
public class SendCodeController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ThirdPartFeignService thirdPartFeignService;

    @Autowired
    private IMailService mailService;

    @Value("${spring.mail.username}")
    private String from;

    @PostMapping("/sms")
    public R sendSMSCode(@Validated @RequestBody CodeVo codeVo) {
        String phone = codeVo.getPhone();
        if(!StringUtils.isEmpty(phone))
            return R.ok("phone is null!");
        //接口防刷,在redis中缓存phone-code
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String prePhone = AuthServerConstant.SMS_CODE_CACHE_PREFIX + phone;
        String v = ops.get(prePhone);
        if (!StringUtils.isEmpty(v)) {
            long pre = Long.parseLong(v.split("_")[1]);
            //如果存储的时间小于60s，说明60s内发送过验证码
            if (System.currentTimeMillis() - pre < 60000) {
                return R.error(BizCodeEnum.SMS_CODE_EXCEPTION.getCode(), BizCodeEnum.SMS_CODE_EXCEPTION.getMsg());
            }
        }
        //如果存在的话，删除之前的验证码
        redisTemplate.delete(prePhone);
        //获取到6位数字的验证码
        String code = String.valueOf((int) ((Math.random() + 1) * 100000));
        //在redis中进行存储并设置过期时间
        ops.set(prePhone, code + "_" + System.currentTimeMillis(), 10, TimeUnit.MINUTES);
        thirdPartFeignService.sendCode(phone, code);
        return R.ok();
    }

    @PostMapping("/email")
    public R sendEmailCode(@Validated @RequestBody CodeVo codeVo, @RequestParam String type) {
        String email = codeVo.getEmail();
        if(!org.springframework.util.StringUtils.hasText(email))
            return R.ok("email is null!");
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String preEmail = AuthServerConstant.EMAIL_CODE_CACHE_PREFIX + email;
        String v = ops.get(preEmail);
        if (org.springframework.util.StringUtils.hasText(v)) {
            long pre = Long.parseLong(v.split("_")[1]);
            //如果存储的时间小于60s，说明60s内发送过验证码
            if (System.currentTimeMillis() - pre < 60000) {
                return R.error(BizCodeEnum.SMS_CODE_EXCEPTION.getCode(), BizCodeEnum.SMS_CODE_EXCEPTION.getMsg());
            }
        }
        redisTemplate.delete(preEmail);
        String code = RandomStringUtils.randomAlphanumeric(6);
        System.out.println(email+": "+code);
        ops.set(preEmail, code + "_" + System.currentTimeMillis(), 10, TimeUnit.MINUTES);
        String sb = "your "+type+" code is:\n" +
                code +
                "\r\n" +
                "expire time is 10 minutes\r\n";
        mailService.sendMail(from,email,"login code", sb);
        return R.ok();
    }



}
