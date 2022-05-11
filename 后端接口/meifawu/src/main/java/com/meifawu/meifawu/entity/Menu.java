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
@TableName("menu")
@ApiModel(value = "Menu对象", description = "")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("点餐id")
    @TableId(value = "menu_id", type = IdType.AUTO)
    private int menuId;

    @ApiModelProperty("用户姓名")
    private String userName;

    @ApiModelProperty("用户性别")
    private String userSex;

    @ApiModelProperty("点餐详情")
    private String menuDetail;

    @ApiModelProperty("点餐的价格")
    private Float menuPrice;



}
