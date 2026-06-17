package com.atafl.lease.web.admin.controller.seckill;

import com.atafl.lease.common.result.Result;
import com.atafl.lease.common.exception.LeaseException;
import com.atafl.lease.model.entity.*;
import com.atafl.lease.model.enums.LeaseSourceType;
import com.atafl.lease.model.enums.LeaseStatus;
import com.atafl.lease.web.admin.mapper.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/seckill")
public class SeckillCallbackController {

    @Autowired
    private ViewAppointmentMapper appointmentMapper;

    @Autowired
    private RoomInfoMapper roomInfoMapper;

    @Autowired
    private ApartmentInfoMapper apartmentInfoMapper;

    @Autowired
    private LeaseAgreementMapper leaseAgreementMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private LeaseTermMapper leaseTermMapper;

    @Autowired
    private PaymentTypeMapper paymentTypeMapper;

    @PostMapping("/callback")
    public Result callback(@RequestParam Long roomId, @RequestParam Long userId) {
        // 1. 创建预约记录
        LeaseAgreement leaseAgreement = new LeaseAgreement();
        LambdaQueryWrapper<UserInfo> userInfoQueryWrapper = new LambdaQueryWrapper<>();
        userInfoQueryWrapper.eq(UserInfo::getId, userId);
        UserInfo userInfo = userInfoMapper.selectOne(userInfoQueryWrapper);
        leaseAgreement.setPhone(userInfo.getPhone());

        leaseAgreement.setName(userInfo.getNickname());

        LambdaQueryWrapper<RoomInfo> roomInfoQueryWrapper = new LambdaQueryWrapper<>();
        roomInfoQueryWrapper.eq(RoomInfo::getId, roomId);
        RoomInfo roomInfo = roomInfoMapper.selectOne(roomInfoQueryWrapper);
        leaseAgreement.setApartmentId(roomInfo.getApartmentId());
        leaseAgreement.setRoomId(roomInfo.getId());
        leaseAgreement.setRent(roomInfo.getRent());

        // 设置租约开始日期（当前日期）
        leaseAgreement.setLeaseStartDate(new Date());
        
        // 设置租约结束日期（假设默认租期为1年，需要根据实际业务调整）
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 1); // 默认1年
        leaseAgreement.setLeaseEndDate(calendar.getTime());
        
        // 设置租期ID（需要查询该房间可用的租期，这里取第一个）
        List<LeaseTerm> leaseTermList = leaseTermMapper.selectListByRoomId(roomId);
        if (leaseTermList != null && !leaseTermList.isEmpty()) {
            leaseAgreement.setLeaseTermId(leaseTermList.get(0).getId());
        }
        
        // 设置押金（默认等于一个月租金，可根据业务调整）
        leaseAgreement.setDeposit(roomInfo.getRent());
        
        // 设置支付类型ID（需要查询该房间可用的支付方式，这里取第一个）
        List<PaymentType> paymentTypeList = paymentTypeMapper.selectListByRoomId(roomId);
        if (paymentTypeList != null && !paymentTypeList.isEmpty()) {
            leaseAgreement.setPaymentTypeId(paymentTypeList.get(0).getId());
        }
        
        // 设置租约状态（秒杀创建，设置为已签约状态）
        leaseAgreement.setStatus(LeaseStatus.SIGNED);
        
        // 设置租约来源（新签）
        leaseAgreement.setSourceType(LeaseSourceType.NEW);
        
        // 设置备注信息
        leaseAgreement.setAdditionalInfo("秒杀活动自动创建");
        
        // 版本号由MyBatis-Plus自动管理，无需手动设置
        
        // 保存租约到数据库
        leaseAgreementMapper.insert(leaseAgreement);

        return Result.ok("预约创建成功");
    }
}