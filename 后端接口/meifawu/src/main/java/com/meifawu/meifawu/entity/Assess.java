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
 * @since 2021-12-30
 */
@Data
@TableName("assess")
@ApiModel(value = "Assess对象", description = "")
public class Assess implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "assess_id", type = IdType.AUTO)
    private Integer assessId;

    private String nickName;

    private Float assessLevelEnvironment;

    private Float assessLevelSkill;

    private Float assessLevelServe;

    private String assessContent;

    private String assessPicture;


}
