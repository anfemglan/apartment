package com.atafl.lease.web.app.service.impl;

import com.atafl.lease.model.entity.ViewAppointment;
import com.atafl.lease.web.app.mapper.ViewAppointmentMapper;
import com.atafl.lease.web.app.service.ApartmentInfoService;
import com.atafl.lease.web.app.service.ViewAppointmentService;
import com.atafl.lease.web.app.vo.apartment.ApartmentItemVo;
import com.atafl.lease.web.app.vo.appointment.AppointmentDetailVo;
import com.atafl.lease.web.app.vo.appointment.AppointmentItemVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liubo
 * @description 针对表【view_appointment(预约看房信息表)】的数据库操作Service实现
 * @createDate 2023-07-26 11:12:39
 */
@Service
public class ViewAppointmentServiceImpl extends ServiceImpl<ViewAppointmentMapper, ViewAppointment>
        implements ViewAppointmentService {

    @Autowired
    private ViewAppointmentMapper viewAppointmentMapper;

    @Autowired
    private ApartmentInfoService apartmentInfoService;
    @Override
    public List<AppointmentItemVo> getAppoitmentListByUserId(Long userId) {


        return viewAppointmentMapper.getAppoitmentListByUserId(userId);
    }


    @Override
    public AppointmentDetailVo getDetailById(Long id) {
        AppointmentDetailVo appointmentDetailVo = new AppointmentDetailVo();
        ViewAppointment viewAppointment = viewAppointmentMapper.selectById(id);
        ApartmentItemVo apartmentItemVo = apartmentInfoService.selectItemById(viewAppointment.getApartmentId());

        BeanUtils.copyProperties(viewAppointmentMapper.selectById(id), appointmentDetailVo);
        appointmentDetailVo.setApartmentItemVo(apartmentItemVo);

        return appointmentDetailVo;
    }
}




