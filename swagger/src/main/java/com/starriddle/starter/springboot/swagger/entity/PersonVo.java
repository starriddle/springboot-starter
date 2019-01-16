package com.starriddle.starter.springboot.swagger.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 实体类
 *
 * @author CYL
 * @date 2019-01-16
 */
@Data
@ApiModel(description = "后台返回的 Person 对象")
public class PersonVo {

    @ApiModelProperty(value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "姓名", example = "Jack")
    private String name;

    @ApiModelProperty(value = "年龄", example = "19")
    private Integer age;

    @ApiModelProperty(value = "生日", example = "2000-01-01")
    private Date birthday;

    @ApiModelProperty(value = "地址", example = "Shanghai")
    private String address;

    @ApiModelProperty(value = "简介", example = "simple describe")
    private String describe;
}
