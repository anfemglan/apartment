package com.atafl.lease.web.admin.controller.schedule;

import com.atafl.lease.model.entity.LeaseAgreement;
import com.atafl.lease.model.enums.LeaseStatus;
import com.atafl.lease.web.admin.service.LeaseAgreementService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduleTasks {
//    @Scheduled(cron = "* * * * * * ")
//    public void test(){
//        System.out.println(new Date());
//    }
    @Autowired
    private LeaseAgreementService service;
    @Scheduled(cron = "0 0 0 * * * ")
    public void checkLeaseStatus(){
        LambdaUpdateWrapper<LeaseAgreement> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.le(LeaseAgreement::getLeaseEndDate, new Date());
        updateWrapper.in(LeaseAgreement::getStatus, LeaseStatus.SIGNED, LeaseStatus.WITHDRAWING);
        updateWrapper.set(LeaseAgreement::getStatus, LeaseStatus.EXPIRED);
        service.update(updateWrapper);
    }
}
