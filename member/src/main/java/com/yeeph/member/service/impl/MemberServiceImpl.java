package com.yeeph.member.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeeph.common.constant.RegisterConstant;
import com.yeeph.common.utils.HttpUtils;
import com.yeeph.common.utils.PageUtils;
import com.yeeph.common.utils.Query;
import com.yeeph.member.dao.MemberDao;
import com.yeeph.member.entity.MemberEntity;
import com.yeeph.member.entity.MemberLevelEntity;
import com.yeeph.member.exception.PhoneNumExistException;
import com.yeeph.member.exception.UserExistException;
import com.yeeph.member.service.MemberLevelService;
import com.yeeph.member.service.MemberService;
import com.yeeph.member.vo.MemberLoginVo;
import com.yeeph.member.vo.MemberRegisterVo;
import com.yeeph.member.vo.SocialUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Autowired
    private MemberLevelService memberLevelService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberEntity> page = this.page(
                new Query<MemberEntity>().getPage(params),
                new QueryWrapper<MemberEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void register(MemberRegisterVo registerVo) {
        //1 检查电话或邮箱是否唯一
        String type = registerVo.getRegisterType();
        if(StringUtils.equals(type, RegisterConstant.PHONE_TYPE)){
            checkPhoneUnique(registerVo.getPhone());
            registerVo.setEmail(null);
        }else if(StringUtils.equals(type, RegisterConstant.EMAIL_TYPE)){
            checkEmailUnique(registerVo.getPhone());
            registerVo.setPhone(null);
        }else{
            throw new RuntimeException("Unknown RegisterType");
        }
        //2 检查用户名是否唯一
        checkUserNameUnique(registerVo.getUserName());
        //3 该用户信息唯一，进行插入
        MemberEntity entity = new MemberEntity();
        //3.1 保存基本信息
        entity.setUsername(registerVo.getUserName());
        entity.setMobile(registerVo.getPhone());
        entity.setEmail(registerVo.getEmail());
        entity.setCreateTime(new Date());
        entity.setUserId(registerVo.getUserId());
        //3.2 使用加密保存密码
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(registerVo.getPassword());
        entity.setPassword(encodePassword);
        //3.3 设置会员默认等级
        //3.3.1 找到会员默认登记
        MemberLevelEntity defaultLevel = memberLevelService.getOne(new QueryWrapper<MemberLevelEntity>().eq("default_status", 1));
        //3.3.2 设置会员等级为默认
        entity.setLevelId(defaultLevel.getId());
        // 4 保存用户信息
        this.save(entity);
    }

    private void checkEmailUnique(String email) {
        Integer count = baseMapper.selectCount(new QueryWrapper<MemberEntity>().eq("email", email));
        if (count > 0) {
            throw new PhoneNumExistException();
        }
    }

    @Override
    public MemberEntity login(MemberLoginVo loginVo) {
        String loginAccount = loginVo.getLoginAccount();
        //以用户名或电话号或邮箱进行查询
        MemberEntity entity = this.getOne(new QueryWrapper<MemberEntity>()
                .select("username","mobile","email","user_id","level_Id","nickname","header","gender","password")
                .eq("username", loginAccount)
                .or()
                .eq("mobile", loginAccount)
                .or()
                .eq("email",loginAccount));
        if (entity != null) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            boolean matches = bCryptPasswordEncoder.matches(loginVo.getPassword(), entity.getPassword());
            if (matches) {
                entity.setPassword("");
                return entity;
            }
        }
        return null;
    }

    @Override
    public MemberEntity login(SocialUser socialUser) {
        MemberEntity uid = this.getOne(new QueryWrapper<MemberEntity>().eq("uid", socialUser.getUid()));
        //1 如果之前未登陆过，则查询其社交信息进行注册
        if (uid == null) {
            Map<String, String> query = new HashMap<>();
            query.put("access_token", socialUser.getAccess_token());
            query.put("uid", socialUser.getUid());
            //调用微博api接口获取用户信息
            String json = null;
            try {
                HttpResponse response = HttpUtils.doGet("https://api.weibo.com", "/2/users/show.json", "get", new HashMap<>(), query);
                json = EntityUtils.toString(response.getEntity());
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONObject jsonObject = JSON.parseObject(json);
            //获得昵称，性别，头像
            String name = jsonObject.getString("name");
            String gender = jsonObject.getString("gender");
            String profile_image_url = jsonObject.getString("profile_image_url");
            //封装用户信息并保存
            uid = new MemberEntity();
            MemberLevelEntity defaultLevel = memberLevelService.getOne(new QueryWrapper<MemberLevelEntity>().eq("default_status", 1));
            uid.setLevelId(defaultLevel.getId());
            uid.setNickname(name);
            uid.setGender("m".equals(gender) ? 0 : 1);
            uid.setHeader(profile_image_url);
            uid.setAccessToken(socialUser.getAccess_token());
            uid.setUid(socialUser.getUid());
            uid.setExpiresIn(socialUser.getExpires_in());
            this.save(uid);
        } else {
            //2 否则更新令牌等信息并返回
            uid.setAccessToken(socialUser.getAccess_token());
            uid.setUid(socialUser.getUid());
            uid.setExpiresIn(socialUser.getExpires_in());
            this.updateById(uid);
        }
        return uid;
    }

    private void checkUserNameUnique(String userName) {
        Integer count = baseMapper.selectCount(new QueryWrapper<MemberEntity>().eq("username", userName));
        if (count > 0) {
            throw new UserExistException();
        }
    }

    private void checkPhoneUnique(String phone) {
        Integer count = baseMapper.selectCount(new QueryWrapper<MemberEntity>().eq("mobile", phone));
        if (count > 0) {
            throw new PhoneNumExistException();
        }
    }

}