package com.starriddle.starter.springboot.demo.util;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Result<T>implements Serializable {

    private static final long serialVersionUID = 495891886719750259L;

    public static <T> Result<T> result(T t) {
        return new Result<T>(t);
    }

    public static <T> Result<T> result(int ret) {
        return new Result<T>(ret);
    }

    public static <T> Result<T> result(int ret, T t) {
        return new Result<T>(ret, t);
    }

    private int ret;

    private String message;

    private T result;

    public Result() {
    }

    public Result(T result) {
        this.ret = 1;
        this.result = result;
    }

    public Result(int returnCode) {
        this.ret = returnCode;
    }

    public Result(int returnCode, T result) {
        this.ret = returnCode;
        this.result = result;
    }

    public Result(int returnCode, String message) {
        this.ret = returnCode;
        this.message = message;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.ret == 1;
    }

    @JsonIgnore
    public boolean isFailure() {
        return this.ret != 1;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
