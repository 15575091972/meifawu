package com.meifawu.meifawu.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value = "Orders对象", description = "")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单id")
    @TableId
    private int orderId;

    @ApiModelProperty("用户id")
    private int userId;

    @ApiModelProperty("理发师id")
    private int baberId;

    @ApiModelProperty("订单价格")
    private Float orderPrice;

    @ApiModelProperty("订单开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty("订单结束时间")
    private LocalDateTime finalTime;

    @ApiModelProperty("订单详情（服务方式）")
    private String orderDetail;

    @ApiModelProperty("订单状态（完成/未完成）")
    private String orderState;

    @ApiModelProperty("订单评价（好评/一般/差评）")
    private String orderEvaluate;



}
