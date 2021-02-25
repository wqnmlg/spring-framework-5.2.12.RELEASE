package org.springframework.practice;


import org.junit.Test;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.practice.bean.Blue;
import org.springframework.practice.bean.Person;
import org.springframework.practice.bean.Student;
import org.springframework.practice.bean.Yello;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Description TODO
 * @Author wuc
 * @Date 2021/1/29 14:44
 * @Version 1.0
 **/
public class BeanTest {

    /**
     * 注册bean的几种方式
     * 1、使用Controller ,Service ,Component 等注解
     * 2、在配置类中使用 Bean
     * 3、使用Import注解
     *  3.1 业务类名.class
     *  3.2 ImportSelector.class
     *  3.3 ImportBeanDefinitionRegistrar.class
     * 4、FactoryBean
     */
    @Import({Person.class/*,MyImportSelector.class,MyImportBeanDefinitionRegistrar.class*/})
    @Configuration
    public static class BeanConfig{

        @Bean("student")
		Student student(){
            return new Student("zhang3",12);
        }

        /*@Bean
        YelloFactoryBean yelloFactoryBean(){
            return new YelloFactoryBean();
        }*/
    }

    public static class YelloFactoryBean implements FactoryBean<Yello> {

        public Yello getObject() throws Exception {
            return new Yello();
        }

        public Class<?> getObjectType() {
            return Yello.class;
        }

        public boolean isSingleton() {
            return true;
        }
    }
    public static class MyImportSelector implements ImportSelector {

        public String[] selectImports(AnnotationMetadata importingClassMetadata) {
            return new String[]{"com.spring.test.bean.Red"};
        }
    }

    public static class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
            this.registerBeanDefinitions(importingClassMetadata,registry);
        }

        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
            RootBeanDefinition beanDefinition = new RootBeanDefinition(Blue.class);
            registry.registerBeanDefinition("blue",beanDefinition);
        }
    }

	@Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (int i=0;i<beanDefinitionNames.length;i++){
            System.out.println(beanDefinitionNames[i]);
        }
    }

    @Test
	public void test02(){
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(2);
		ids.add(3);
		ids.add(4);
		ids.add(5);
		ids.sort(new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				return 0;
			}

			@Override
			public boolean equals(Object obj) {
				return false;
			}
		});
	}
}
