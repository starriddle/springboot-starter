package com.starriddle.starter.springboot.swagger.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.starriddle.starter.springboot.swagger.entity.PersonPo;
import com.starriddle.starter.springboot.swagger.entity.PersonReq;
import com.starriddle.starter.springboot.swagger.entity.PersonVo;
import com.starriddle.starter.springboot.swagger.service.PersonService;
import com.starriddle.starter.springboot.swagger.util.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * description
 *
 * @author CYL
 * @date 2019-01-16
 */
@RestController
@RequestMapping("/swagger2")
// @api：value 在1.5以后无用，tags定义类标签，默认为ab-cd <- AbCd.class
@Api(value = "Person", tags = "Person Controller")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(path = "/person", method = RequestMethod.POST, produces = "application/json")
    // 接口注释 文档说明
    // tags定义接口标签，默认为类标签，
    // 如自定义标签，则会忽略类默认标签，但不会忽略类自定义标签
    // 即：一旦自定义了类/接口的标签(类默认标签消失)，在类和接口的多个自定义标签下均能看到该接口，
    //     否则(未自定义任何标签)在类默认标签下看到
    @ApiOperation(value = "添加 person", notes = "添加新的 person", tags = {"person-v1"}, httpMethod = "POST")
    // 返回注释，如下自定义返回码200的说明
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
    })
    public Result<PersonVo> createPerson(PersonReq req) {
        PersonPo po = personService.create(req);
        return Result.success(PersonPo.tranformtoVo(po));
    }

    @RequestMapping(path = "/person/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ApiOperation(value = "删除 person", notes = "根据 id 删除对应的 person", tags = {"person-v2"}, httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要删除的 person id", required = true)
    })
    public Result<Boolean> deletePerson(@PathVariable Long id) {
        Boolean success = personService.delete(id);
        return success ? Result.success(null) : Result.fail(1, "delete fail");
    }

    @RequestMapping(path = "/person/{id}", method = RequestMethod.PUT, produces = "application/json")
    @ApiOperation(value = "更新 person", notes = "根据 id 更新对应的 person", tags = {"person-v2"}, httpMethod = "PUT")
    // 路径变量/表单参数 简单类型对象的入参说明，非 RequestBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的 person id", required = true)
    })
    public Result<PersonVo> updatePerson(@PathVariable Long id, @RequestBody(required = false) PersonReq req) {
        req.setId(id);
        PersonPo po = personService.update(req);
        return po==null ? Result.fail("person 不存在") : Result.success(PersonPo.tranformtoVo(po));
    }

    @RequestMapping(path = "/person/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "查询 person", notes = "根据 id 查询对应的 person", tags = {"person-v1"}, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要查询的 person id", required = true)
    })
    public Result<PersonVo> getPerson(@PathVariable Long id) {
        PersonPo po = personService.getById(id);
        return po==null ? Result.fail("person 不存在") : Result.success(PersonPo.tranformtoVo(po));
    }

    @RequestMapping(path = "/persons", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取 person 列表", notes = "获取所有 person，返回列表", tags = {"person-v1"}, httpMethod = "GET")
    public Result<List<PersonVo>> getPersons() {
        List<PersonPo> pos = personService.getAll();
        List<PersonVo> vos = pos.stream().map(PersonPo::tranformtoVo).collect(Collectors.toList());
        return Result.success(vos);
    }
}
