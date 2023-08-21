package com.yeeph.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeeph.common.to.SkuHasStockVo;
import com.yeeph.common.to.mq.OrderTo;
import com.yeeph.common.to.mq.StockLockedTo;
import com.yeeph.common.utils.PageUtils;
import com.yeeph.ware.entity.WareSkuEntity;
import com.yeeph.ware.vo.WareSkuLockVo;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author Ethan
 * @email hongshengmo@163.com
 * @date 2020-05-27 23:15:25
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addStock(Long skuId, Long wareId, Integer skuNum);

    List<SkuHasStockVo> getSkuHasStocks(List<Long> ids);

    Boolean orderLockStock(WareSkuLockVo lockVo);

    void unlock(StockLockedTo stockLockedTo);

    void unlock(OrderTo orderTo);
}

