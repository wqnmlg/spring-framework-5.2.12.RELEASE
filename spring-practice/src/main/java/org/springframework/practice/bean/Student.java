package org.springframework.practice.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Description TODO
 * @Author wuc
 * @Date 2021/1/29 14:44
 * @Version 1.0
 **/
public class Student implements ApplicationContextAware {

    private ApplicationContext applicationContext ;
    public Student() {
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    private String name="wang5";

    private Integer age ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void init(){
        this.setName("li4");
    }

    public void destory(){
        System.out.println("destory Student");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext =applicationContext;
    }
}
