package com.meifawu.meifawu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author 孟子弘
 * @since 2021-12-28
 */
@Data
@ApiModel(value = "Store对象", description = "")
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("美发店id")
    @TableId(value = "store_id", type = IdType.AUTO)
    private Integer storeId;

    @ApiModelProperty("美发店位置")
    private String storeLocation;

    @ApiModelProperty("美发店水平")
    private String storeLevel;

    @ApiModelProperty("美发店介绍")
    private String storeIntroduction;



}
