package service.testAnnotation;

import org.springframework.stereotype.Service;
import service.annotation.InjectValue;
@InjectValue
public class AnnotationTest01 {
    @InjectValue("zhangsan")
    private String name01;
    @InjectValue("lisi")
    private String name02;

    public String getName01() {
        return name01;
    }

    public void setName01(String name01) {
        System.out.println("set方法01");
        this.name01 = name01;
    }

    public String getName02() {
        return name02;
    }

    public void setName02(String name02) {
        System.out.println("set方法02");
        this.name02 = name02;
    }

    @Override
    public String toString() {
        return "AnnotationTest01{" +
                "name01='" + name01 + '\'' +
                ", name02='" + name02 + '\'' +
                '}';
    }
}
