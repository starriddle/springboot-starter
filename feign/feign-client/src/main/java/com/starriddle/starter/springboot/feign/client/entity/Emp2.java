package com.starriddle.starter.springboot.feign.client.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CYL
 * @date 2018-04-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp2 {

    /**
     * emp id
     */
    private long id;
    /**
     * emp name
     */
    private String name;
    /**
     * emp age
     */
    private int age;
    /**
     * emp birthday
     */
    private String birthday;
    /**
     * emp address
     */
    private String address;
    /**
     * emp dept id
     */
    private long deptId;
    /**
     * emp org id
     */
    private long orgId;

}
