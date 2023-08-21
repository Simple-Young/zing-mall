package com.yeeph.product.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.yeeph.common.exception.BizCodeEnum;
import com.yeeph.common.utils.R;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class GulimallSentinelConfig {
    @Bean
    public BlockExceptionHandler urlBlockHandler() {
        return new BlockExceptionHandler() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response, BlockException ex) throws IOException {
                R r = R.error(BizCodeEnum.TO_MANY_REQUEST.getCode(), BizCodeEnum.TO_MANY_REQUEST.getMsg());
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(JSON.toJSONString(r));
            }
        };
    }
}
