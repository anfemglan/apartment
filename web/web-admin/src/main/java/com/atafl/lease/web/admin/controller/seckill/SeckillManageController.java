package com.atafl.lease.web.admin.controller.seckill;

import com.atafl.lease.web.admin.service.SeckillManageService;
import com.atafl.lease.common.vo.SeckillCreateVo;
import com.atafl.lease.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/seckill")
public class SeckillManageController {

    @Autowired
    private SeckillManageService seckillManageService;

    @PostMapping("/create")
    public Result createSeckill(@RequestBody SeckillCreateVo vo) {
        boolean success = seckillManageService.createSeckillActivity(vo);
        return success ? Result.ok("创建成功") : Result.fail(201,"创建失败");
    }
}