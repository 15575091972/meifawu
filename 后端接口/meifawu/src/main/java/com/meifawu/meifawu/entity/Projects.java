package com.meifawu.meifawu.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author 孟子弘
 * @since 2021-12-24
 */
@Data
@TableName("projects")
@ApiModel(value = "Projects对象", description = "")
public class Projects implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "project_id", type = IdType.AUTO)
    private Integer projectId;

    private String projectName;


}
