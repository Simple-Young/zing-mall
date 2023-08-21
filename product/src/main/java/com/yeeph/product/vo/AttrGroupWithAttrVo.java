package com.yeeph.product.vo;

import com.yeeph.product.entity.AttrEntity;
import com.yeeph.product.entity.AttrGroupEntity;
import lombok.Data;

import java.util.List;

@Data
public class AttrGroupWithAttrVo extends AttrGroupEntity {
    private List<AttrEntity> attrs;
}
