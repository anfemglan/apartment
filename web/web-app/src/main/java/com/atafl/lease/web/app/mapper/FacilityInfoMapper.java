package com.atafl.lease.web.app.mapper;

import com.atafl.lease.model.entity.FacilityInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【facility_info(配套信息表)】的数据库操作 Mapper
* @createDate 2023-07-26 11:12:39
* @Entity com.atafl.lease.model.entity.FacilityInfo
*/
public interface FacilityInfoMapper extends BaseMapper<FacilityInfo> {

    List<FacilityInfo> selectListById(Long id);
}




