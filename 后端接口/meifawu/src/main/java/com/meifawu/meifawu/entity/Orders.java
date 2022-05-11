package com.meifawu.meifawu.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @author 刘诚可
 * @since 2021-12-20
 */
@Data
@TableName("orders")
@ApiModel(value = "Orders对象", description = "")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单id")
    @TableId(value = "order_id", type = IdType.AUTO)
    private int orderId;

    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("用户姓名")
    private String userName;

    @ApiModelProperty("用户电话")
    private String phone;

    @ApiModelProperty("理发师姓名")
    private String barberName;

    @ApiModelProperty("预约日期")
    private LocalDate appointmentDate;

    @ApiModelProperty("预约时间段")
    private String timeslot;

    @ApiModelProperty("订单价格")
    private Float orderPrice;

    @ApiModelProperty("订单详情（服务方式）")
    private String orderDetail;

    @ApiModelProperty("其他要求")
    private String otherDemand;

    @ApiModelProperty("商家确认状态")
    private String verifyState;

    @ApiModelProperty("订单状态（完成/未完成）")
    private String orderState;

    @ApiModelProperty("订单评价（好评/一般/差评）")
    private String orderEvaluate;

}
