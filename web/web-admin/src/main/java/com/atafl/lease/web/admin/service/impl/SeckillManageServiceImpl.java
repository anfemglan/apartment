package com.atafl.lease.web.admin.service.impl;

import com.atafl.lease.web.admin.service.SeckillManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.atafl.lease.common.vo.SeckillCreateVo;

@Service
public class SeckillManageServiceImpl implements SeckillManageService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean createSeckillActivity(SeckillCreateVo vo) {
        // TODO: 调用 seckill-service 的 /admin/seckill/create 接口
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8082/admin/seckill/create", vo, String.class);
        // 暂不实现，只建框架
        return true;
    }
}