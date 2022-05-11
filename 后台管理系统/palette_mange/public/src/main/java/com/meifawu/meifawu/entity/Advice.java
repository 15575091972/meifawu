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
@ApiModel(value = "Advice对象", description = "")
public class Advice implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("投诉建议id")
    @TableId
    private int adviceId;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("投诉详情")
    private String adviceDetail;


}
