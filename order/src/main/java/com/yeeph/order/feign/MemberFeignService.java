package com.yeeph.order.feign;

import com.yeeph.order.vo.MemberAddressVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("member")
public interface MemberFeignService {

    @RequestMapping("member/memberreceiveaddress/getAddressByUserId")
    List<MemberAddressVo> getAddressByUserId(@RequestBody Long userId);
}
