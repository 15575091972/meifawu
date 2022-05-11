package com.meifawu.meifawu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

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
 * @since 2021-12-28
 */
@Data
@TableName("user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户的电话号码，作为用户id")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String nickName;

    private String headerUrl;

    @ApiModelProperty("用户是否为会员")
    private String userCategory;

    @ApiModelProperty("用户余额")
    private Float userBalance;

    @ApiModelProperty("用户积分")
    private Integer userScore;

}
