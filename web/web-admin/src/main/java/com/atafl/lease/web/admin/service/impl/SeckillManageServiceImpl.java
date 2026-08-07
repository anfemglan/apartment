package com.atafl.lease.web.admin.service.impl;

import com.atafl.lease.web.admin.service.SeckillManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.atafl.lease.common.vo.SeckillCreateVo;

@Service
public class SeckillManageServiceImpl implements SeckillManageService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.seckill.url}")
    private String seckillUrl;

    @Override
    public boolean createSeckillActivity(SeckillCreateVo vo) {
        // 调用 seckill-service 的 /admin/seckill/create 接口
        ResponseEntity<String> response = restTemplate.postForEntity(seckillUrl + "/admin/seckill/create", vo, String.class);
        return true;
    }
}