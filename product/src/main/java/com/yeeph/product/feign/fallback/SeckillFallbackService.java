package com.yeeph.product.feign.fallback;

import com.yeeph.common.exception.BizCodeEnum;
import com.yeeph.common.utils.R;
import com.yeeph.product.feign.SeckillFeignService;
import org.springframework.stereotype.Component;

@Component
public class SeckillFallbackService implements SeckillFeignService {
    @Override
    public R getSeckillSkuInfo(Long skuId) {
        return R.error(BizCodeEnum.READ_TIME_OUT_EXCEPTION.getCode(), BizCodeEnum.READ_TIME_OUT_EXCEPTION.getMsg());
    }
}
