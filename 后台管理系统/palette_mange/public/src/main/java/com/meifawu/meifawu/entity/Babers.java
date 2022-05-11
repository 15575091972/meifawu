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
 * @since 2021-12-12
 */
@Data
@ApiModel(value = "Babers对象", description = "")
public class Babers implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("理发师id")
    //主键要带上@TableId注解，不然会出错
    @TableId
    private int barber_id;

    @ApiModelProperty("理发师姓名")
    private String barberName;

    @ApiModelProperty("理发师年龄")
    private String barberAge;

    @ApiModelProperty("理发师电话")
    private String barberPhone;

    @ApiModelProperty("理发师介绍")
    private String barberIntroduction;

    @ApiModelProperty("理发师好评率")
    private String baberGoodrate;

    @ApiModelProperty("理发师薪水")
    private Integer baberSalary;

    @ApiModelProperty("理发师级别（店长、顾问、精英）")
    private String baberLevel;

    @ApiModelProperty("理发师图片")
    private String baberImage;


}
