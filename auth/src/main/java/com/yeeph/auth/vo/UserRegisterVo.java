package com.yeeph.auth.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class UserRegisterVo {

    @NotEmpty(message = "用户名不能为空")
    @Length(min = 6, max = 19, message = "用户名长度在6—18字符")
    private String userName;

    @NotEmpty(message = "密码必须填写")
    @Length(min = 6, max = 19, message = "密码必须是6—18字符")
    private String password;

    @Length(min = 11, max = 11, message = "手机号必须为11位")
    @Pattern(regexp = "^[1]([3-9])[0-9]{9}$", message = "手机号格式不正确")
    private String phone;

    @Email(message = "Error email format!")
    private String email;

    @NotEmpty(message = "验证码不能为空")
    private String code;

    @NotEmpty
    private String registerType;

    private String userId;
}
