package org.springframework.practice.bean;

/**
 * @Description TODO
 * @Author wuc
 * @Date 2021/1/29 15:13
 * @Version 1.0
 **/
public class Person {
    private String name="person";

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

}
