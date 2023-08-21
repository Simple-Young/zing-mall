package com.yeeph.member.vo;

import lombok.Data;

@Data
public class MemberRegisterVo {

    private String userName;

    private String password;

    private String phone;

    private String email;

    private String registerType;

    private String userId;
}
