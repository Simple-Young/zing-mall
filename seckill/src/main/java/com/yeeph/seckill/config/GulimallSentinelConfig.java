package com.yeeph.seckill.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.yeeph.common.exception.BizCodeEnum;
import com.yeeph.common.utils.R;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GulimallSentinelConfig implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        R r = R.error(BizCodeEnum.SECKILL_EXCEPTION.getCode(), BizCodeEnum.SECKILL_EXCEPTION.getMsg());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(r));
    }
}
