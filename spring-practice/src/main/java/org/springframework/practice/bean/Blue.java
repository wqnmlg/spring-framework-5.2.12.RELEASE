package org.springframework.practice.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Description TODO
 * @Author wuc
 * @Date 2021/1/29 15:21
 * @Version 1.0
 **/
public class Blue {

    private String name ="hello";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        this.setName("wuc");
    }

    @PreDestroy
    public void destroy() throws Exception {
        System.out.println("destory Blue");

    }
}
