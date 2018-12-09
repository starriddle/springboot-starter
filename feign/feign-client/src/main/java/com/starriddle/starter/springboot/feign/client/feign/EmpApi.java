package com.starriddle.starter.springboot.feign.client.feign;

import java.time.LocalDate;
import java.util.List;

import com.starriddle.starter.springboot.feign.client.entity.Dept;
import com.starriddle.starter.springboot.feign.client.entity.Emp;
import com.starriddle.starter.springboot.feign.client.entity.Org;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 *
 * 使用application/json、application/xml等content-type，
 * 才可使用feign的body参数，和服务端的@RequestBody参数
 *
 * @author CYL
 * @date 2018-04-25
 */
@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface EmpApi {

    /**
     * feign POST 说明
     * POST方法最多只能有一个Body参数，
     * 否则服务端启动报错，提示该方法body参数太多
     *
     * Body参数在服务端必须使用@RequestBody接收，
     * 使用无注解参数接收，服务器实际得到一个空构造器生成的新对象
     * 使用@RequestParam参数接收，会被拦截返回错误信息，提示相应id的对象不存在
     *
     * 其他参数只能使用@Param参数，
     *
     * url 中必须将@param参数都写上，否则传输不过去
     *
     * 使用@RequestBody参数 / 无注解参数接收，服务器实际得到一个空构造器生成的新对象
     * 使用@RequestParam参数接收，会被拦截返回错误信息，提示相应id的对象不存在
     *
     * @param emp
     * @return
     */
    @RequestLine("POST /emp/create")
    boolean create(Emp emp);

    /**
     * Gson decode json到实体类时，如遇LocalDate类则解析异常
     * java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING
     * 服务器编码将LocalDate转为json："1999-01-01"
     * 客户端解码无法将"1999-01-01"转换为LocalDate
     * @param emp
     * @return
     */
    @RequestLine("POST /emp/update")
    Emp update(Emp emp);

    /**
     * feign GET 说明
     * GET方法，如不使用@Param注释(默认使用Body参数)，或不在url中书写参数信息，会转为使用POST方法，
     *
     * 此时如服务端设置为GET方法，则会报错 Request method 'POST' not supported
     * 如服务端可接收POST请求，则见 feign POST 说明
     *
     * @param id
     * @return
     */
    @RequestLine("GET /emp/getById?id={id}")
    Emp getById(@Param("id") long id);

    @RequestLine("GET /emp/getByDept")
    List<Emp> getByDept(@Param("dept") Dept dept);

    @RequestLine("GET /emp/getByOrg")
    List<Emp> getByOrg(Org org);

    @RequestLine("GET /emp/getByDeptAndOrg")
    List<Emp> getByDeptAndOrg(@Param("dept") Dept dept, @Param("org") Org org);

    @RequestLine("GET /emp/getByCondition")
    List<Emp> getByCondition(@Param("name") String name, @Param("name") long age, @Param("birthday") LocalDate birthday, @Param("dept") Dept dept, @Param("org") Org org);
}
