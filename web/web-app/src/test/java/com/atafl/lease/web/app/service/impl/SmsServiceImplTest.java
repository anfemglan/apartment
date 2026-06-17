package com.atafl.lease.web.app.service.impl;

import com.atafl.lease.web.app.service.SmsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SmsServiceImplTest {

    @Autowired
    private SmsService smsService;
    @Test
    void sendcode() {
        smsService.sendcode("19175088259","1234");
    }
}