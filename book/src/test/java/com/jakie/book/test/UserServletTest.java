package com.jakie.book.test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class UserServletTest {

    private static void updateUserAndPassword(){
        System.out.println("updateUserAndPassword方法");
    }

    private static void register(){
        System.out.println("register方法");
    }

    private static void login(){
        System.out.println("login方法");
    }

    public static void main(String[] args) {
        String action = "updateUserAndPassword";
        try {
            Method declaredMethod = UserServletTest.class.getDeclaredMethod(action);
            declaredMethod.invoke(new UserServletTest());
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}