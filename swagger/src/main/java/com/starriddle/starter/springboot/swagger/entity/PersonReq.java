package com.starriddle.starter.springboot.swagger.entity;

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
// value 默认使用类名，可自定义，但swagger页面显示不友好，不建议使用
@ApiModel(value = "Person Request", description = "创建/修改 person 对象的请求参数")
public class PersonReq {

    // name 默认为属性名称，可自定义，但只有作为requestParam表单参数时才有用，Json类型requestBody中没用
    @ApiModelProperty(name = "id", value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(name = "person name", value = "姓名", example = "Jack")
    private String name;

    @ApiModelProperty(value = "年龄", example = "19")
    private Integer age;

    @ApiModelProperty(value = "生日", example = "1546272000000")
    private Long birthday;

    @ApiModelProperty(value = "地址", example = "Shanghai")
    private String address;

    @ApiModelProperty(value = "简介", example = "simple describe")
    private String describe;
}
