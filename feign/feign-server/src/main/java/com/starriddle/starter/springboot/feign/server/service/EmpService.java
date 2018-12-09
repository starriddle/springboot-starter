package com.starriddle.starter.springboot.feign.server.service;

import com.starriddle.starter.springboot.feign.server.entity.Dept;
import com.starriddle.starter.springboot.feign.server.entity.Emp;
import com.starriddle.starter.springboot.feign.server.entity.Org;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CYL
 * @date 2018-04-25
 */
@Service
public class EmpService {

    public boolean create(Emp emp) {
        System.out.println("create Emp: " + (emp==null?null:emp.toString()));
        System.out.println("return: " + true);
        return true;
    }

    public Emp update(Emp emp) {
        System.out.println("update Emp: " + (emp==null?null:emp.toString()));
        Emp result = new Emp(9001, "jack", 27, LocalDate.of(1950,1,1), "shanghai, china", 1,1);
        System.out.println("return: " + result);
        return result;
    }

    public Emp getById(long id) {
        System.out.println("get Emp by Id: " + id);
        Emp emp = new Emp(9001, "jack", 27, LocalDate.of(1950,1,1), "shanghai, china", 1,1);
        System.out.println("return: " + emp);
        return emp;
    }

    public List<Emp> getByDept(Dept dept) {
        System.out.println("get Emp by dept: " + (dept==null?null:dept.toString()));
        Emp emp = new Emp(9001, "jack", 27, LocalDate.of(1950,1,1), "shanghai, china", 1,1);
        List<Emp> list = new ArrayList<>();
        list.add(emp);
        System.out.println("return: " + list);
        return list;
    }

    public List<Emp> getByOrg(Org org) {
        System.out.println("get Emp by org: " + (org==null?null:org.toString()));
        Emp emp = new Emp(9001, "jack", 27, LocalDate.of(1950,1,1), "shanghai, china", 1,1);
        List<Emp> list = new ArrayList<>();
        list.add(emp);
        System.out.println("return: " + list);
        return list;
    }

    public List<Emp> getByDeptAndOrg(Dept dept, Org org) {
        System.out.println("get Emp by dept: " + (dept==null?null:dept.toString()));
        System.out.println("and org: " + (org==null?null:org.toString()));

        Emp emp = new Emp(9001, "jack", 27, LocalDate.of(1950,1,1), "shanghai, china", 1,1);
        List<Emp> list = new ArrayList<>();
        list.add(emp);
        System.out.println("return: " + list);
        return list;
    }

    public List<Emp> getByCondition(String name, long age, LocalDate birthday, Dept dept, Org org) {
        System.out.println("get Emp by condition: name=" + name + ", age=" + age);
        System.out.println("and birthday=" + (birthday==null?null:birthday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        System.out.println("and dept: " + (dept==null?null:dept.toString()));
        System.out.println("and org: " + (org==null?null:org.toString()));

        Emp emp = new Emp(9001, "jack", 27, LocalDate.of(1950,1,1), "shanghai, china", 1,1);
        List<Emp> list = new ArrayList<>();
        list.add(emp);
        System.out.println("return: " + list);
        return list;
    }
}
