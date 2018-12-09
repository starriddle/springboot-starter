package com.starriddle.starter.springboot.feign.server.entity;

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
public class Dept {

    /**
     * dept id
     */
    private long id;
    /**
     * dept name
     */
    private String name;
    /**
     * dept address
     */
    private String address;
    /**
     * dept org id
     */
    private long orgId;

}
