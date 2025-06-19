package com.jakie.book.utils;

import org.apache.commons.beanutils.BeanUtils;


import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;


/***
 * @Author jakie
 * @Description //TODO 将web请求的参数 一次注入到对应的bean 中
 *                      不再通过这种方式 构建
 *                      String username = request.getParameter("username");
 *                      String password = request.getParameter("password");
 *                      String email = request.getParameter("email");
 *                      User user = new User(username....password)
 * @Date 14:29 2025/6/18
 * @Param
 * @return
 **/
public class WebUtils {

    public static <T> T copyParamToBean(Map map, T bean){

        try {
            System.out.println("注入前 "+bean);
            System.out.println(bean);
            //通过参数名  找到对应bean类中的set方法进行注入
            BeanUtils.populate(bean,map);
            System.out.println("注入后 "+bean);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return bean;
    }
}
