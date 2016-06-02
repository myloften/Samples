package com.loften.samples.Other;

import java.io.ObjectStreamException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by loften on 16/3/28.
 * 主要用来测试代码块
 * 单例模式
 */
public class Singleton {

    //懒汉模式
    private static Singleton instance;

    private Singleton() {
    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

//public class Singleton {
//    //Double Check Lock(DCL)方式
//    private static Singleton singleton = null;
//
//    private Singleton() {
//    }
//
//    public static Singleton getSingleton() {
//        if (singleton == null) {
//            synchronized (Singleton.class) {
//                if (singleton == null) {
//                    singleton = new Singleton();
//                }
//            }
//        }
//    }
//}
//
//public class Singleton {
//    //静态内部类单例模式
//    private Singleton() {
//    }
//
//    public static Singleton getInstance() {
//        return SingletonHolder.sInstance;
//    }
//
//    /*
//    * 静态内部类
//    */
//    private static class SingletonHolder {
//        private static final Singleton sInstance = new Singleton();
//    }
//}
//
////枚举单例
//public enum SingletonEnum {
//    INSTANCE;
//
//    public void doSomething() {
//        System.out.print("do sth.");
//    }
//}
//
////使用容器实现单例模式
//public class SingletonManager {
//    private static Map<String, Object> objMap = new HashMap<String, Object>();
//
//    private SingletonManager() {
//    }
//
//    public static void registerService(String key, Object instance) {
//        if (!objMap.containsKey(key)) {
//            objMap.put(key, instance);
//        }
//    }
//
//    public static Object getService(String key) {
//        return objMap.get(key);
//    }
//
//}
//
//private Object readResolve() throws ObjectStreamException{
//    return sInstance;
//}
