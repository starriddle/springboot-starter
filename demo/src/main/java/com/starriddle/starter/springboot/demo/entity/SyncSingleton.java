package com.starriddle.starter.springboot.demo.entity;

/**
 * 单例模式懒加载2
 *
 * 除内部类解决方案外的最佳实现
 *
 * 将实例化的过程抽象出来, 放在一个sychronized方法中执行, 能够实现懒加载与并发安全
 *
 * @author CYL
 * @date 2018-12-07
 */
public class SyncSingleton {

    private static SyncSingleton instance = null;

    private SyncSingleton(){}

    public static SyncSingleton getInstance(){
        if (instance==null){
            createInstance();
        }
        return instance;
    }

    private static synchronized SyncSingleton createInstance(){
        if (instance==null){
            instance = new SyncSingleton();
        }
        return instance;
    }
}
