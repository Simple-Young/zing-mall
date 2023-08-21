package com.yeeph.member.exception;

public class EmailOrPhoneNullException extends RuntimeException{
    public EmailOrPhoneNullException(){
        super("邮箱 和 手机号都为空");
    }
}
