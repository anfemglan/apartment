package com.atafl.lease.web.admin.mapper;

import com.atafl.lease.model.entity.RoomInfo;
import com.atafl.lease.web.admin.vo.room.RoomDetailVo;
import com.atafl.lease.web.admin.vo.room.RoomItemVo;
import com.atafl.lease.web.admin.vo.room.RoomQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
* @author liubo
* @description 针对表【room_info(房间信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.atafl.lease.model.RoomInfo
*/
public interface RoomInfoMapper extends BaseMapper<RoomInfo> {

    IPage<RoomItemVo> pageRoomItemByQuery(IPage<RoomItemVo> page, RoomQueryVo queryVo);

    RoomDetailVo getRoomDetailById(Long id);

    List<RoomItemVo> getAvailableRoomsByDistrictId(Long districtId);
}




