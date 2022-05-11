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
@TableName("servers")
@ApiModel(value = "Servers对象", description = "")
public class Servers implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("服务人员id")
    @TableId(value = "server_id", type = IdType.AUTO)
    private int serverId;

    @ApiModelProperty("服务人员姓名")
    private String serverName;

    @ApiModelProperty("服务人员年龄")
    private String serverAge;

    @ApiModelProperty("服务人员电话")
    private String serverPhone;

    @ApiModelProperty("服务人员薪水")
    private Integer serverSalary;

}
