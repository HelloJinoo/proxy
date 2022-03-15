package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BasicTest {

    @Test
    void basicConfig(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(BasicConfig.class);

        A beanA = ac.getBean("beanA", A.class);
        beanA.helloA();

        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean(B.class));
    }

    @Configuration
    static class BasicConfig{

        @Bean(name = "beanA")
        public A a(){
            return new A();
        }

    }


    @Slf4j
    static class A{

        public void helloA(){
            log.info("hello A");
        }
    }


    @Slf4j
    static class B{

        public void helloB(){
            log.info("hello B");
        }
    }
}
