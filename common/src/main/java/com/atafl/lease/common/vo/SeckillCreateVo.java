package com.atafl.lease.common.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SeckillCreateVo {
    private List<Long> roomIds;//房间号
    private BigDecimal seckillPrice;//秒杀价格
    private LocalDateTime startTime;//秒杀开始时间
    private LocalDateTime endTime;//秒杀结束时间
}