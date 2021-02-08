package org.springframework.practice.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Description TODO
 * @Author wuc
 * @Date 2021/1/29 15:18
 * @Version 1.0
 **/
public class Red  implements InitializingBean, DisposableBean {

    private String name ="hello";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void afterPropertiesSet() throws Exception {
        this.setName("wuc");
    }

    public void destroy() throws Exception {
        System.out.println("destory Red");

    }
}
