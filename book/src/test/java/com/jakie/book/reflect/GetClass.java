package com.jakie.book.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLOutput;

public class GetClass {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        //获取类的三种方式
//      1.Class.forName("全类名");
//        2.类名.class
//        3.对象.getClass()
        Person person = new Person();
        Class aClass = person.getClass();
        System.out.println(aClass);

        //获取public属性数组
        System.out.println("获取public属性");
        Field[] fields = aClass.getFields();
        for (Field idx : fields) {
            System.out.println(idx);
        }
        //通过属性名获取单个public属性
        System.out.println(aClass.getField("a"));

        //获取所有属性
        System.out.println("获取所有属性(没有访问修饰符限制)");
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field idx : declaredFields) {
            System.out.println(idx);
        }

        Field d = aClass.getDeclaredField("d");
        System.out.println(d);

        //field变量.get(field所在对象) 获取属性值
        System.out.println("获取属性值");
        d.setAccessible(true);  //暴力破解 可以获取非public属性的值
        System.out.println(d.get(person));

        Constructor[] constructors = aClass.getConstructors();
        for(Constructor idx:constructors){
            System.out.println(idx);
        }

        //构造器实例化对象
        System.out.println("构造器实例化对象");
        Constructor constructor = aClass.getConstructor();
        Object o = constructor.newInstance();
        //空参构造器可以使用 class.newInstance获取
        Person person1 = Person.class.newInstance();
        System.out.println(o+" "+person);


        System.out.println("获取方法");
        Method[] methods = aClass.getMethods();//还会获取到父类的方法
        Method[] declaredMethods = aClass.getDeclaredMethods();

        for(Method idx:declaredMethods) {
            System.out.println(idx);
        }

        System.out.println("===========================");
        Method setA = aClass.getDeclaredMethod("setA", String.class);
        setA.setAccessible(true);
        //isAccessible 表示是否可以被绕过 Java 语言访问控制检查 默认为false
        System.out.println("是否可以绕过访问修饰符检查"+setA.isAccessible());
        setA.invoke(person, "person_A");
        System.out.println(person.getA());




    }
}

class Person {
    public String a = "a";
    protected String b = "b";
    String c = "c";
    private String d = "d";

    public Person() {
    }

    public Person(String a, String b, String c, String d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public String getA() {
        return a;
    }

    protected void setA(String a) {
        this.a = a;
    }

    String getB() {
        return b;
    }

    private void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    @Override
    public String toString() {
        return "Person{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                '}';
    }
}