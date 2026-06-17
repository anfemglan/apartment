package com.atafl.lease.web.app.service.impl;

import com.atafl.lease.model.entity.ApartmentInfo;
import com.atafl.lease.model.entity.FacilityInfo;
import com.atafl.lease.model.entity.LabelInfo;
import com.atafl.lease.model.enums.ItemType;
import com.atafl.lease.web.app.mapper.*;
import com.atafl.lease.web.app.service.ApartmentInfoService;
import com.atafl.lease.web.app.vo.apartment.ApartmentDetailVo;
import com.atafl.lease.web.app.vo.apartment.ApartmentItemVo;
import com.atafl.lease.web.app.vo.graph.GraphVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author liubo
 * @description 针对表【apartment_info(公寓信息表)】的数据库操作 Service 实现
 * @createDate 2023-07-26 11:12:39
 */
@Service
public class ApartmentInfoServiceImpl extends ServiceImpl<ApartmentInfoMapper, ApartmentInfo>
        implements ApartmentInfoService {
    @Autowired
    private ApartmentInfoMapper apartmentInfoMapper;

    @Autowired
    private LabelInfoMapper labelInfoMapper;

    @Autowired
    private GraphInfoMapper graphInfoMapper;

    @Autowired
    private FacilityInfoMapper facilityInfoMapper;

    @Autowired
    private RoomInfoMapper roomInfoMapper;


    @Override
    public ApartmentItemVo selectItemById(Long id) {
        ApartmentInfo apartmentInfo = apartmentInfoMapper.selectItemById(id);

        List<LabelInfo> labelInfoList = labelInfoMapper.selectListById(id);
        List<GraphVo> graphVoList = graphInfoMapper.selectListByItemTypeAndId(ItemType.APARTMENT,id);
        BigDecimal minRent = roomInfoMapper.selectMinRentByApartmentId(id);

        ApartmentItemVo apartmentItemVo = new ApartmentItemVo();

        BeanUtils.copyProperties(apartmentInfo, apartmentItemVo);
        apartmentItemVo.setGraphVoList(graphVoList);
        apartmentItemVo.setLabelInfoList(labelInfoList);
        apartmentItemVo.setMinRent(minRent);
        return apartmentItemVo;
    }

    @Override
    public ApartmentDetailVo selectDetailById(Long id) {

        ApartmentInfo apartmentInfo = apartmentInfoMapper.selectItemById(id);


        List<LabelInfo> labelInfoList = labelInfoMapper.selectListById(id);
        List<GraphVo> graphVoList = graphInfoMapper.selectListByItemTypeAndId(ItemType.APARTMENT,id);
        List<FacilityInfo> facilityInfoList = facilityInfoMapper.selectListById(id);
        BigDecimal minRent = roomInfoMapper.selectMinRentByApartmentId(id);

        ApartmentDetailVo apartmentDetailVo = new ApartmentDetailVo();

        BeanUtils.copyProperties(apartmentInfo, apartmentDetailVo);
        apartmentDetailVo.setGraphVoList(graphVoList);
        apartmentDetailVo.setLabelInfoList(labelInfoList);
        apartmentDetailVo.setFacilityInfoList(facilityInfoList);
        apartmentDetailVo.setMinRent(minRent);
        return apartmentDetailVo;
    }
}




