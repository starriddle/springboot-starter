package com.starriddle.starter.springboot.swagger.util;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Rest接口返回结果封装类
 *
 * @author chen
 */
@Data
@ApiModel(description = "封装后的返回结果")
public class Result<T>implements Serializable {

    private static final long serialVersionUID = 495891886719750259L;

    @ApiModelProperty(value = "业务处理结果返回码", example = "0", required = true)
    private int code;

    @ApiModelProperty(value = "业务处理异常消息", example = "对象不存在")
    private String message;

    @ApiModelProperty(value = "业务处理返回结果")
    private T result;

    public Result() {
    }

    public Result(int code) {
        this.code = code;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.code == 0;
    }

    @JsonIgnore
    public boolean isFailure() {
        return this.code != 0;
    }

    public static <T> Result<T> success(T result) {
        return new Result<T>(0, null, result);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<T>(1, message);
    }

    public static <T> Result<T> fail(int code, String message) {
        return new Result<T>(code, message);
    }

    public static <T> Result<T> result(int code) {
        return new Result<T>(code);
    }

    public static <T> Result<T> result(int code, String message) {
        return new Result<T>(code, message);
    }

    public static <T> Result<T> result(int code, String message, T result) {
        return new Result<T>(code, message, result);
    }

}
