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
@ApiModel(value = "Menu对象", description = "")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("点餐id")
    @TableId
    private int menuId;

    @ApiModelProperty("用户id")
    private int userId;

    @ApiModelProperty("点餐详情")
    private String menuDetail;

    @ApiModelProperty("点餐的价格")
    private Float menuPrice;



}
