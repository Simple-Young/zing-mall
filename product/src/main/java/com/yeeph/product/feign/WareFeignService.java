package com.yeeph.product.feign;

import com.yeeph.common.to.SkuHasStockVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("gulimall-ware")
public interface WareFeignService {

    @RequestMapping("/ware/waresku/getSkuHasStocks")
    List<SkuHasStockVo> getSkuHasStocks(@RequestBody List<Long> ids);
}
