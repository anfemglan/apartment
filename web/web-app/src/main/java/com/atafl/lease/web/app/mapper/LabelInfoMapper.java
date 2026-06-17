package com.atafl.lease.web.app.mapper;

import com.atafl.lease.model.entity.LabelInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【label_info(标签信息表)】的数据库操作 Mapper
* @createDate 2023-07-26 11:12:39
* @Entity com.atafl.lease.model.entity.LabelInfo
*/
public interface LabelInfoMapper extends BaseMapper<LabelInfo> {

    List<LabelInfo> selectListById(Long id);
}




