package com.meifawu.meifawu.entity;

import java.io.Serializable;
import java.time.LocalDate;

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
@TableName("sales")
@ApiModel(value = "Sales对象", description = "")
public class Sales implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("销售情况id")
    @TableId(value = "sale_id", type = IdType.AUTO)
    private int saleId;

    @ApiModelProperty("销售日期")
    private LocalDate saleDate;

    @ApiModelProperty("销售金额")
    private Float salePrice;

    @ApiModelProperty("销售的订单数")
    private int saleOrders;

}
