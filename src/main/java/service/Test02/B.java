package service.Test02;

/**
 * @ClassName B
 * @Description TODO
 * @Author heqin
 * @Date 2021/8/24 9:16
 * @Version 1.0
 **/
public class B extends A {
    String phone;
    String addr;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "B{" +
                "phone='" + phone + '\'' +
                ", addr='" + addr + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
