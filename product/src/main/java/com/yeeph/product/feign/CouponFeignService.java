package com.yeeph.product.feign;

import com.yeeph.common.to.SkuReductionTo;
import com.yeeph.common.to.SpuBoundTo;
import com.yeeph.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    @PostMapping("/coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundTo spuBoundTo);

    @PostMapping("/coupon/skufullreduction/saveInfo")
    R saveSkuReductionTo(@RequestBody SkuReductionTo skuReductionTo);
}
