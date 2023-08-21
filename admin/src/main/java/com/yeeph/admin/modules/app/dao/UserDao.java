package com.yeeph.admin.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeeph.admin.modules.app.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
