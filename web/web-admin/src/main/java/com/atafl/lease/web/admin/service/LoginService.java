package com.atafl.lease.web.admin.service;

import com.atafl.lease.web.admin.vo.login.CaptchaVo;
import com.atafl.lease.web.admin.vo.login.LoginVo;
import com.atafl.lease.web.admin.vo.system.user.SystemUserInfoVo;

public interface LoginService {

    CaptchaVo getCaptcha();

    String login(LoginVo loginVo);

    SystemUserInfoVo getLoginUserInfoById(Long userId);
}
