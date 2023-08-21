package com.yeeph.member.feign;

import com.yeeph.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("coupon")
public interface CouponFeignService {
    @RequestMapping("/coupon/member/list")
    R memberCoupons();
}
