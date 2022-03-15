package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

public class BeanPostProcessorTest {

    @Test
    void basicConfig(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(BeanPostProcssorConfig.class);

        B beanB = ac.getBean("beanA", B.class);
        beanB.helloB();

        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean(A.class));
    }

    @Configuration
    static class BeanPostProcssorConfig{

        @Bean(name = "beanA")
        public A a(){
            return new A();
        }

        @Bean
        public AToBProcessor helloPostProcessor(){
            return new AToBProcessor();
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

    @Slf4j
    static class AToBProcessor implements BeanPostProcessor{
        @Nullable
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            log.info("beanName = {}, bean = {}", beanName, bean);
            if(bean instanceof  A){
                return new B();
            }
            return bean;
        }
    }
}
