package com.atafl.lease.web.app.service;

public interface SmsService {
    void sendcode(String phone, String code);
}
