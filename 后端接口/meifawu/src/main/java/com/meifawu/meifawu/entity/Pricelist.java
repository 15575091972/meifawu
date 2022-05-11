package com.meifawu.meifawu.entity;

import java.io.Serializable;

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
@TableName("pricelist")
@ApiModel(value = "Pricelist对象", description = "")
public class Pricelist implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("价目id")
    @TableId(value = "pricelist_id", type = IdType.AUTO)
    private int pricelistId;

    @ApiModelProperty("服务方式")
    private String pricelistWay;

    @ApiModelProperty("服务级别")
    private String pricelistLevel;

    @ApiModelProperty("服务价格")
    private Float pricelistPrice;




}
