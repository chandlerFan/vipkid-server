package com.quxueyuan.server.service.impl;


import com.quxueyuan.bean.domain.Sku;
import com.quxueyuan.server.api.service.SkuService;
import com.quxueyuan.server.dao.SkuMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SkuServiceImpl implements SkuService {

    @Resource
    private SkuMapper skuMapper;


    @Override
    public Sku getSkuIfoById(Integer skuId) {
        Sku sku=skuMapper.slectSkuById(skuId);
        return sku;
    }
}
