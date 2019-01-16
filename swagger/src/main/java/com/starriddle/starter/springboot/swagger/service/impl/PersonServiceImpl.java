package com.starriddle.starter.springboot.swagger.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.starriddle.starter.springboot.swagger.entity.PersonPo;
import com.starriddle.starter.springboot.swagger.entity.PersonReq;
import com.starriddle.starter.springboot.swagger.service.PersonService;

/**
 * Person Service implement
 *
 * @author CYL
 * @date 2019-01-16
 */
@Service
public class PersonServiceImpl implements PersonService {

    private Map<Long, PersonPo> map = new ConcurrentHashMap<>();
    @Override
    public PersonPo create(PersonReq req) {
        PersonPo po = PersonPo.tranformFromReq(req);
        po.setId((long)map.size());
        po.setCreateTime(new Date());
        po.setUpdateTime(new Date());
        map.put(po.getId(), po);
        return po;
    }

    @Override
    public Boolean delete(Long id) {
        PersonPo po = map.remove(id);
        return true;
    }

    @Override
    public PersonPo update(PersonReq req) {
        PersonPo po = map.get(req.getId());
        if (po != null) {
            po.setName(req.getName()==null ? po.getName() : req.getName());
            po.setAge(req.getAge()==null ? po.getAge() : req.getAge());
            po.setBirthday(req.getBirthday()==null ? po.getBirthday() : new Date(req.getBirthday()));
            po.setAddress(req.getAddress()==null ? po.getAddress() : req.getAddress());
            po.setDescribe(req.getDescribe()==null ? po.getDescribe() : req.getDescribe());
            po.setUpdateTime(new Date());
            map.put(po.getId(), po);
        }
        return po;
    }

    @Override
    public PersonPo getById(Long id) {
        return map.get(id);
    }

    @Override
    public List<PersonPo> getAll() {
        return new ArrayList<>(map.values());
    }
}
