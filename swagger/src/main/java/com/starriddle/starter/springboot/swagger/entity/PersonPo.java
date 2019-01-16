package com.starriddle.starter.springboot.swagger.entity;

import java.util.Date;

import lombok.Data;

/**
 * 实体类
 *
 * @author CYL
 * @date 2019-01-16
 */
@Data
public class PersonPo {

    private Long id;

    private String name;

    private Integer age;

    private Date birthday;

    private String address;

    private String describe;

    private Date createTime;

    private Date updateTime;

    public static PersonPo tranformFromReq(PersonReq from) {
        PersonPo to = new PersonPo();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setAge(from.getAge());
        to.setBirthday(new Date(from.getBirthday()));
        to.setAddress(from.getAddress());
        to.setDescribe(from.getDescribe());
        return to;
    }

    public static PersonVo tranformtoVo(PersonPo from) {
        PersonVo to = new PersonVo();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setAge(from.getAge());
        to.setBirthday(from.getBirthday());
        to.setAddress(from.getAddress());
        to.setDescribe(from.getDescribe());
        return to;
    }
}
