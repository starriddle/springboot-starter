package com.starriddle.starter.springboot.swagger.service;

import java.util.List;

import com.starriddle.starter.springboot.swagger.entity.PersonPo;
import com.starriddle.starter.springboot.swagger.entity.PersonReq;

/**
 * Person Service
 *
 * @author CYL
 * @date 2019-01-16
 */
public interface PersonService {

    /**
     * 创建 person，返回 id
     * @param req
     * @return
     */
    PersonPo create(PersonReq req);

    /**
     * 根据 id 删除 person
     * @param id
     * @return
     */
    Boolean delete(Long id);

    /**
     * 根据 id 修改 person
     * @param req
     * @return
     */
    PersonPo update(PersonReq req);

    /**
     * 根据 id 获取 person
     * @param id
     * @return
     */
    PersonPo getById(Long id);

    /**
     * 获取所有 person 的 list
     * @return
     */
    List<PersonPo> getAll();
}
