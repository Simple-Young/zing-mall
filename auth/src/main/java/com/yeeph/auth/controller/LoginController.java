package com.yeeph.auth.controller;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeeph.auth.feign.MemberFeignService;
import com.yeeph.auth.vo.RedisLoginVo;
import com.yeeph.auth.vo.SocialUser;
import com.yeeph.auth.vo.UserLoginVo;
import com.yeeph.common.constant.AuthServerConstant;
import com.yeeph.common.utils.R;
import com.yeeph.common.utils.SnowFlakeTo8CharsUtils;
import com.yeeph.common.vo.MemberResponseVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Resource
    private MemberFeignService memberFeignService;

    @Autowired
    private SnowflakeGenerator snowflakeGenerator;

    @PostMapping("/phone")
    public R loginWithPhone(UserLoginVo vo){
        // TODO
        return R.ok();
    }

    @PostMapping("email")
    public R LoginWithEmail(@Validated @RequestBody UserLoginVo vo, HttpServletResponse response){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        // 获取验证码
        String key = AuthServerConstant.EMAIL_CODE_CACHE_PREFIX + vo.getLoginAccount();
        String code = ops.get(key);
        if(StringUtils.isEmpty(code))
            return R.error("验证码已过期");
        code = code.split("_")[0];
        if(!StringUtils.equals(code,vo.getCodeOrType())){
            return R.error("验证码不同");
        }
        vo.setCodeOrType(AuthServerConstant.Email_TYPE);
        //验证通过,远程调用登录服务 查询账号并返回
        R r = memberFeignService.login(vo);
        if(r.getCode()!=0)
            return r;
        String jsonString = JSON.toJSONString(r.get("memberEntity"));
        MemberResponseVo memberResponseVo = JSON.parseObject(jsonString, new TypeReference<MemberResponseVo>() {
        });
        //生成token，放到redis
        Long snowId = snowflakeGenerator.next();
        String token = AuthServerConstant.LOGIN_TOKEN_PREFIX + SnowFlakeTo8CharsUtils.long2String(snowId);
        RedisLoginVo loginVo = new RedisLoginVo();
        loginVo.setAccount(memberResponseVo.getEmail());
        loginVo.setUserId(memberResponseVo.getUserId());
        ObjectMapper objectMapper = new ObjectMapper();
        String loginVoJsonStr = null;
        try {
            loginVoJsonStr = objectMapper.writeValueAsString(loginVo);
        } catch (JsonProcessingException e) {
            return R.error(500,"loginVo 转换错误");
        }
        redisTemplate.delete(key);
        ops.set(token, loginVoJsonStr,30, TimeUnit.MINUTES);
        Cookie cookie = new Cookie("token",token);
        response.addCookie(cookie);
        return R.ok().put(AuthServerConstant.LOGIN_USER,loginVo);
    }

    @PostMapping("social")
    public R LoginWithSocial(SocialUser socialUser){
        return memberFeignService.login(socialUser);
    }

    @RequestMapping("auth")
    public R isLogin(HttpServletRequest request){
        //查询用户是否已经登录,并重新设置token时间,设置过滤器
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String token = null;
        Cookie[] cookies = request.getCookies();
        if(cookies == null)
            return R.error("用户未登录");
        for (Cookie cookie : cookies) {
            if(StringUtils.equals(cookie.getName(),"token")){
                token = cookie.getValue();
                break;
            }
        }
        try {
            if(!StringUtils.isEmpty(token)) {
                String loginJsonStr = ops.get(token);
                ObjectMapper objectMapper = new ObjectMapper();
                RedisLoginVo redisLoginVo = objectMapper.readValue(loginJsonStr, RedisLoginVo.class);
                return R.ok().put(AuthServerConstant.LOGIN_USER, redisLoginVo);
            }
        } catch (JsonProcessingException e) {
            return R.error(500,"loginJsonStr 转换错误");
        }
        return R.error("用户未登录");
    }


}
