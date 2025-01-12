package com.bookMyShow.bookmyshow.dataloader;

import com.bookMyShow.bookmyshow.userfilter.UserContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.bookMyShow.bookmyshow.userfilter.UserContext.getUserThreadId;

public class Main {
    public static void main(String[] args) {

//        DataLoader<String,String> dataLoader = k-> k.hashCode()+"hello";
//
//
//        Map<String,String> mp = new HashMap<>();
//
//        DelegatingMap<String,String> dmp = new DelegatingMap<>(dataLoader,mp);
//
//       Object o1 = new String("hello");
//       Object o2 = new String("World");
//
//       dmp.find(o1);
//       dmp.find(o2);
//
//        System.out.println(mp);
//        DataLoader<Integer,String> dataLoader = k-> k.hashCode()+"hello";
//
//        Map<Integer,String> mp = new HashMap<>();
//
//        DelegatingMap<Integer,String> dmp = new DelegatingMap<>(dataLoader,mp);
//
//        dmp.find(2);
//        dmp.find(5);
//        System.out.println(dmp.find(2));
//        System.out.println(mp);

        UserContext.setUserThreadId("viraj");
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ExecutorService delegating = new DelegatingExecutorService(executorService);
        delegating.execute(() -> doSomething());
    }

    private static void doSomething() {
        System.out.println(getUserThreadId());
        System.out.println("hello");
    }
}
