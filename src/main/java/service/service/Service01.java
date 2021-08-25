package service.service;

import java.util.Arrays;

public class Service01 {

    public static void main(String[] args) {
        User u = new User(1,1,"a","a");
      ThreadLocal  t  = new ThreadLocal();
      t.set(u);
      new Thread(()->{}).start();
    }
}
