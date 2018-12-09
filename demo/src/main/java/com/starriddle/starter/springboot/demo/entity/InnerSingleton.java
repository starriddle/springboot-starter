package com.starriddle.starter.springboot.demo.entity;

/**
 * 单例模式懒加载1
 *
 * 采用内部类的解决方案, 实现单例模式的懒加载（推荐）
 *
 * 1. 内部类只在需要的时候才会被类加载器加载，实现了懒加载，即在需要的时候才实例化出一个Manager,而且是唯一的一个
 * 2. 由于instance是static final类型，保证了内存中只有这样一个实例存在，而且只能被赋值一次，从而保证了线程安全性
 * （即使在高并发的情况下多个线程同时访问getInstance()方法 也能够保证实例的唯一性）
 *
 * @author CYL
 * @date 2018-12-07
 */
public class InnerSingleton {

    private InnerSingleton(){}

    public static InnerSingleton getInstance(){
        return InnerHolder.instance;
    }

    private static class InnerHolder {
        private static final InnerSingleton instance = new InnerSingleton();
    }
}
