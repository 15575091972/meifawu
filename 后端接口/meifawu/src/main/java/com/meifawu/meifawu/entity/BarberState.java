package com.meifawu.meifawu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author 孟子弘
 * @since 2021-12-28
 */

@TableName("barberstate")
@Data
@ApiModel(value = "BarberState对象", description = "")
public class BarberState implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("状态编号")
    @TableId(value = "state_id", type = IdType.AUTO)
    private Integer stateId;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("日期")
    private LocalDate theDate;

    @ApiModelProperty("理发师名字")
    private String barberName;

    @ApiModelProperty("8:00-10:00空闲或繁忙")
    private String t1State;

    @ApiModelProperty("10:00-12:00空闲或繁忙")
    private String t2State;

    @ApiModelProperty("14:00-16:00空闲或繁忙")
    private String t3State;

    @ApiModelProperty("16:00-18:00空闲或繁忙")
    private String t4State;

    @ApiModelProperty("20:00-22:00空闲或繁忙")
    private String t5State;

    @TableField(exist = false)
    private Barbers barbers;
}
