package org.springframework.practice;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.practice.bean.Blue;
import org.springframework.practice.bean.Student;
import org.springframework.practice.bean.Red;

/**
 * @Description TODO
 * @Author wuc
 * @Date 2021/1/29 15:32
 * @Version 1.0
 **/
public class LifycycleTest {

    /**
     * Bean 生命周期
     *  1、Bean 注解 指定初始化 initMethod  ，销毁方法 destroyMethod
     *       initMethod 对象创建完成并赋值好之后调用初始化方法
     *       destroyMethod 单实例容器关闭的时候销毁 ，多实例自行销毁
     *  2、实现 InitializingBean 和  DisposableBean 的初始化和销毁方法
     *      afterPropertiesSet ： Bean实例化完成并赋值完毕之后调用初始化方法
     *  3、使用 PostConstruct 注解 ,PreDestroy 注解
     *  4、BeanPostProcessor bean初始化前后进行处理
     *      postProcessBeforeInitialization 任何初始化方法调用之前调用
     *      postProcessAfterInitialization 所有初始化方法之后调用
     *      --->
         *      //完成自动注入
         *       populateBean
         *      //所有初始化开始之前调用
         *      applyBeanPostProcessorsBeforeInitialization
         *      //调用初始化方法
         *      invokeInitMethods
         *      //所有初始化完成之后调用
         *      applyBeanPostProcessorsAfterInitialization
     */

    public static class MyBeanPostProcessor implements BeanPostProcessor {

        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            System.out.println("postProcessBeforeInitialization:" + "beanName=" +beanName);
            return bean;
        }

        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            System.out.println("postProcessAfterInitialization:" + "beanName=" +beanName);
            Student student = (Student) bean;
            student.setName("66");
            return student;
        }
    }

    @Configuration
    public static class BeanConfig{

        @Bean
        MyBeanPostProcessor myBeanPostProcessor(){
            return new MyBeanPostProcessor();
        }
        @Bean(value = "student",initMethod = "init",destroyMethod = "destory")
        Student student(){
            return new Student("zhang3",12);
        }

    }

    @Test
	public void test01(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
		Student bean = applicationContext.getBean(Student.class);
		System.out.println(bean.getName());
		applicationContext.close();
	}

}
