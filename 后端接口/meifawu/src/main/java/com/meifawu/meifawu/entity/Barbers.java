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
 * @since 2021-12-12
 */
@TableName("barbers")
@Data
@ApiModel(value = "Babers对象", description = "")
public class Barbers implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("理发师id")
    //主键要带上@TableId注解，不然会出错
    @TableId(value = "barber_id", type = IdType.AUTO)
    private int barberId;

    @ApiModelProperty("理发师姓名")
    private String barberName;

    @ApiModelProperty("理发师年龄")
    private String barberAge;

    @ApiModelProperty("理发师电话")
    private String barberPhone;

    @ApiModelProperty("理发师介绍")
    private String barberIntroduction;

    @ApiModelProperty("理发师好评率")
    private float barberGoodrate;

    @ApiModelProperty("理发师薪水")
    private Integer barberSalary;

    @ApiModelProperty("理发师级别（店长、顾问、精英）")
    private String barberLevel;

    @ApiModelProperty("理发师图片")
    private String barberImage;

    @ApiModelProperty("理发师累计服务人数")
    private String serveCount;

    @ApiModelProperty("理发师擅长")
    private String begoodat;

}
