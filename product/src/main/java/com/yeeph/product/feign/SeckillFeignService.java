package com.yeeph.product.feign;

import com.yeeph.common.utils.R;
import com.yeeph.product.feign.fallback.SeckillFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "gulimall-seckill",fallback = SeckillFallbackService.class)
public interface SeckillFeignService {
    @ResponseBody
    @GetMapping(value = "/getSeckillSkuInfo/{skuId}")
    R getSeckillSkuInfo(@PathVariable("skuId") Long skuId);
}
