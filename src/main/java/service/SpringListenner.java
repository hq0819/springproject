package service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import service.service.Service01;
import service.service.Service02;
import service.service.Service03;
import service.service.Service04;
import service.testAnnotation.AnnotationTest01;

@Service
public class SpringListenner implements ApplicationContextAware {
    ApplicationContext applicationContext=null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Service03 bean = applicationContext.getBean(Service03.class);
        System.out.println(bean);
    }



}
