package service.annotation;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import service.testAnnotation.AnnotationTest01;

import java.lang.reflect.Field;
import java.util.Map;

public class InjectValueService implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(InjectValue.class);
        if (beansWithAnnotation.size()!=0){
            for (Object o:beansWithAnnotation.values()){
                Class<?> aClass = o.getClass();
                Field[] fields = aClass.getDeclaredFields();
                for (Field f : fields){
                    f.setAccessible(true);
                    InjectValue annotation = f.getAnnotation(InjectValue.class);
                    String value = annotation.value();
                    try {
                        f.set(o,value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

        System.out.println(applicationContext.getBean(AnnotationTest01.class));


    }
}
