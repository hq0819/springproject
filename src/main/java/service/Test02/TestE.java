package service.Test02;

import org.springframework.beans.BeanUtils;

/**
 * @ClassName TestE
 * @Description TODO
 * @Author heqin
 * @Date 2021/8/24 9:18
 * @Version 1.0
 **/
public class TestE {
    public static void main(String[] args) {
        B b = new B();
        b.setName("zx");
        b.setAge("18");
        b.setAddr("xxxx");
        b.setPhone("1900");
        B b1 = new B();
        C c = new C();
        BeanUtils.copyProperties(b,c);

        System.out.println(c);
        System.out.println(b);

    }
}
