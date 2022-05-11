package com.meifawu.meifawu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

/**
 * <p>
 * 
 * </p>
 *
 * @author 孟子弘
 * @since 2021-12-28
 */
@Data
@TableName("investrecord")
@ApiModel(value = "Investrecord对象", description = "")
public class Investrecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("充值记录id")
    @TableId(value = "invest_id", type = IdType.AUTO)
    private Integer investId;

    @ApiModelProperty("用户微信昵称")
    private String nickName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("充值日期")
    private LocalDateTime investDate;

    @ApiModelProperty("充值金额")
    private Float investMoney;

}
