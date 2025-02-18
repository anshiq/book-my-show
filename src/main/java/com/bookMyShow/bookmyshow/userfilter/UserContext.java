package com.bookMyShow.bookmyshow.userfilter;

import lombok.experimental.UtilityClass;


public class UserContext {
    private final static ThreadLocal<String> userThreadId = new ThreadLocal<>();

    public static void setUserThreadId(String str){
        userThreadId.set(str);
    }

    public static String getUserThreadId(){
        return userThreadId.get();
    }

    public static ThreadLocal<String> createCopy() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set(getUserThreadId());
        return threadLocal;
    }
}
