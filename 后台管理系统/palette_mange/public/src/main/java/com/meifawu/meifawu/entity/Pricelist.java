package com.meifawu.meifawu.entity;

import java.io.Serializable;

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
@ApiModel(value = "Pricelist对象", description = "")
public class Pricelist implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("价目id")
    @TableId
    private int pricelistId;

    @ApiModelProperty("服务方式")
    private String pricelistWay;

    @ApiModelProperty("店长服务价格")
    private Float pricelistDzPrice;

    @ApiModelProperty("顾问服务价格")
    private Float pricelistGwPrice;

    @ApiModelProperty("精英服务价格")
    private Float pricelistJyPrice;



}
