package com.meifawu.meifawu.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author 孟子弘
 * @since 2021-12-24
 */
@Data
@TableName("timeslotcount")
@ApiModel(value = "Timeslotcount对象", description = "")
public class Timeslotcount implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("日期")
    @TableId(value = "the_date", type = IdType.AUTO)
    private LocalDate theDate;

    @ApiModelProperty("时间段1的预约人数")
    private Integer timeSlot1Count;

    private Integer timeSlot2Count;

    private Integer timeSlot3Count;

    private Integer timeSlot4Count;

    private Integer timeSlot5Count;

    private Integer maxCount;



}
