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
@TableName("advice")
@ApiModel(value = "Advice对象", description = "")
public class Advice implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("投诉建议id")
    @TableId(value = "advice_id", type = IdType.AUTO)
    private int adviceId;

    @ApiModelProperty("用户id")
    private String nickName;

    @ApiModelProperty("投诉详情")
    private String adviceDetail;

    private String phone;
}
