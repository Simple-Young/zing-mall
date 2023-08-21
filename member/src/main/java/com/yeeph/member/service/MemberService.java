package com.yeeph.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeph.common.utils.PageUtils;
import com.yeeph.member.entity.MemberEntity;
import com.yeeph.member.vo.MemberLoginVo;
import com.yeeph.member.vo.MemberRegisterVo;
import com.yeeph.member.vo.SocialUser;

import java.util.Map;

public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void register(MemberRegisterVo registerVo);

    MemberEntity login(MemberLoginVo loginVo);

    MemberEntity login(SocialUser socialUser);
}

