package com.yeeph.common.utils;

import com.yeeph.common.constant.HashIdsSaltConstant;
import org.apache.commons.lang3.RandomUtils;
import org.hashids.Hashids;

import java.util.Date;

public class HashIdsUtils {

    public static String RandomOneUserId(){
        Hashids hashids = new Hashids(HashIdsSaltConstant.USERID_SALT,8,Constant.ALPHA_BET);
        String userId = hashids.encode(new Date().getTime() - RandomUtils.nextInt(),
                new Date().getTime() - RandomUtils.nextInt(),
                new Date().getTime() - RandomUtils.nextInt());
        return Constant.USER_ID_PRE+userId;
    }
}
