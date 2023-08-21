package com.yeeph.auth;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import com.yeeph.common.constant.RedisConstant;
import com.yeeph.common.utils.HashIdsUtils;
import com.yeeph.common.utils.SnowFlakeTo8CharsUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class AuthApplicationTests {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private SnowflakeGenerator snowflakeGenerator;

    @Test
    void addUserId(){

        for(int i=0;i<10000;i++){
            String userId = HashIdsUtils.RandomOneUserId();
            Long add = redisTemplate.opsForSet().add(RedisConstant.REDIS_USER_IDS_KEY, userId);
        }
    }

    @Test
    void testSnowId(){
        Long next = snowflakeGenerator.next();

        System.out.println(next);
    }

    @Test
    void testSnowFlake2Str(){
        Long next = snowflakeGenerator.next();
        String s = SnowFlakeTo8CharsUtils.long2String(next);
        System.out.println(s);
    }


    void testRegx(){
        String regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$|^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String account = "1366154735@qq.com";

    }

}
