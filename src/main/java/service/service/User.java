package service.service;

/**
 * @ClassName User
 * @Description TODO
 * @Author heqin
 * @Date 2021/8/20 14:34
 * @Version 1.0
 **/
public class User {
    int k1;
    int k2;
    String str1;
    String str2;

    public int getK1() {
        return k1;
    }

    public void setK1(int k1) {
        this.k1 = k1;
    }

    public int getK2() {
        return k2;
    }

    public void setK2(int k2) {
        this.k2 = k2;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public User(int k1, int k2, String str1, String str2) {
        this.k1 = k1;
        this.k2 = k2;
        this.str1 = str1;
        this.str2 = str2;
    }

    public User() {
    }
}
