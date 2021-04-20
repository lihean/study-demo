package com.example.study.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
@Order(1)
@Slf4j
public class BeanFactoryPostProcessorExtension implements BeanFactoryPostProcessor{

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // ...
        System.out.print("CustomBeanFactoryPostProcessor ...");
        Iterator<String> beanNames = beanFactory.getBeanNamesIterator();
    }
}
