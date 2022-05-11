package com.meifawu.meifawu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;

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
 * @since 2021-12-23
 */
@Data
@TableName("publicity")
@ApiModel(value = "Publicity对象", description = "")
public class Publicity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "publicity_id", type = IdType.AUTO)
    private Integer publicityId;

    private String publicityName;

    private LocalDate publicityDate;

    private String publicityContent;

}
