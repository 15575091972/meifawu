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
@TableName("admins")
@ApiModel(value = "Admins对象", description = "")
public class Admins implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("管理员id")
    @TableId(value = "admin_id", type = IdType.AUTO)
    private int adminId;

    @ApiModelProperty("管理员用户名")
    private String adminName;

    @ApiModelProperty("管理员所属门店")
    private String adminAgency;

    @ApiModelProperty("管理员密码")
    private String adminPassword;

    @ApiModelProperty("管理员介绍")
    private String adminIntroduction;
}
