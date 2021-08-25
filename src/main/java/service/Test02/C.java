package service.Test02;

/**
 * @ClassName C
 * @Description TODO
 * @Author heqin
 * @Date 2021/8/24 9:17
 * @Version 1.0
 **/
public class C extends A {
    String email;
    String tel;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "C{" +
                "email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
