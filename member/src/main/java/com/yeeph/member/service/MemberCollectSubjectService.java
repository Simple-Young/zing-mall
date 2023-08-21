package com.yeeph.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeph.common.utils.PageUtils;
import com.yeeph.member.entity.MemberCollectSubjectEntity;

import java.util.Map;


public interface MemberCollectSubjectService extends IService<MemberCollectSubjectEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

