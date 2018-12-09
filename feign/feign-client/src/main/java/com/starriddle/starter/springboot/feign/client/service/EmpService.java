package com.starriddle.starter.springboot.feign.client.service;

import com.starriddle.starter.springboot.feign.client.entity.Dept;
import com.starriddle.starter.springboot.feign.client.entity.Emp;
import com.starriddle.starter.springboot.feign.client.entity.Org;
import com.starriddle.starter.springboot.feign.client.feign.EmpApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author CYL
 * @date 2018-04-25
 */
@Service
public class EmpService {

    @Autowired
    EmpApi server;

    public boolean create(Emp emp) {
        System.out.println("create Emp: " + (emp == null?null:emp.toString()));
        emp = new Emp(9002, "jack", 27, LocalDate.of(1950,1,1), "shanghai, china", 1,1);
        System.out.println("change create Emp: " + (emp == null?null:emp.toString()));

        System.out.println("POST to server/emp/create");
        boolean result =  server.create(emp);
        System.out.println("return: " + result);
        return result;
    }

    public Emp update(Emp emp) {
        System.out.println("update Emp: " + (emp==null?null:emp.toString()));
        emp = new Emp(9002, "jack", 27, LocalDate.of(1950,1,1), "shanghai, china", 1,1);
        System.out.println("change update Emp: " + (emp==null?null:emp.toString()));

        System.out.println("POST to server/emp/update");
        Emp result =  server.update(emp);
        System.out.println("return: " + result);
        return result;
    }

    public Emp getById(long id) {
        System.out.println("get Emp by Id: " + id);

        System.out.println("GET to server/emp/getById");
        Emp result = server.getById(id);
        System.out.println("return: " + result);
        return result;
    }

    public List<Emp> getByDept(Dept dept) {
        System.out.println("get Emp by dept: " + (dept==null?null:dept.toString()));
        dept = new Dept();
        dept.setId(10);
        System.out.println("change get Emp by dept: " + (dept==null?null:dept.toString()));

        System.out.println("GET to server/emp/getByOrg");
        List<Emp> result = server.getByDept(dept);
        System.out.println("return: " + result);
        return result;
    }

    public List<Emp> getByOrg(Org org) {
        System.out.println("get Emp by org: " + (org==null?null:org.toString()));
        org = new Org();
        org.setId(100);
        System.out.println("change to get Emp by org: " + (org==null?null:org.toString()));

        System.out.println("GET to server/emp/getByOrg");
        List<Emp> result = server.getByOrg(org);
        System.out.println("return: " + result);
        return result;
    }

    public List<Emp> getByDeptAndOrg(Dept dept, Org org) {
        System.out.println("get Emp by deptandorg: dept=" + (dept==null?null:dept.toString()));
        System.out.println("and org=" + (org==null?null:org.toString()));
        dept = new Dept();
        dept.setId(10);
        org = new Org();
        org.setId(100);
        System.out.println("change to get Emp by deptandorg: dept=" + (dept==null?null:dept.toString()));
        System.out.println("and org=" + (org==null?null:org.toString()));

        System.out.println("GET to server/emp/getByDeptAndOrg");
        List<Emp> result = server.getByDeptAndOrg(dept,org);
        System.out.println("return: " + result);
        return result;
    }

    public List<Emp> getByCondition(String name, long age, LocalDate birthday, Dept dept, Org org) {
        System.out.println("get Emp by condition: name=" + name + ", age=" + age);
        System.out.println("and birthday=" + (birthday==null?null:birthday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        System.out.println("and dept=" + (dept==null?null:dept.toString()));
        System.out.println("and org=" + (org==null?null:org.toString()));
        dept = new Dept();
        dept.setId(10);
        birthday = LocalDate.of(1950,1,1);
        org = new Org();
        org.setId(100);
        System.out.println("change to get Emp by condition: name=" + name + ", age=" + age);
        System.out.println("and birthday=" + (birthday==null?null:birthday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        System.out.println("and dept=" + (dept==null?null:dept.toString()));
        System.out.println("and org=" + (org==null?null:org.toString()));

        System.out.println("GET to server/emp/getByCondition");
        List<Emp> result = server.getByCondition(name,age,birthday,dept,org);
        System.out.println("return: " + result);
        return result;
    }

}
