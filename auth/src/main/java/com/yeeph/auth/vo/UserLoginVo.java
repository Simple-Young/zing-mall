package com.yeeph.auth.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserLoginVo {

    @NotNull
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$|^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "账号格式错误！")
    private String loginAccount;

    @NotNull
    private String password;

    @NotNull
    private String codeOrType;
}
