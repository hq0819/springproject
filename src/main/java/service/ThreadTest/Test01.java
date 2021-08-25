package service.ThreadTest;

import javax.sound.sampled.FloatControl;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class Test01 {
   static Thread t1=null;
   static Thread t2=null;
   static char[] a = "1234567".toCharArray();
   static char[] b = "ABCDEFG".toCharArray();
    public static void main(String[] args) {
         t1 = new Thread(() -> {
            for (int i = 0; i < a.length; i++) {
                printNum(i);
                LockSupport.unpark(t2);
                LockSupport.park();

            }
        });
         t2 = new Thread(() -> {
            for (int i = 0; i < a.length; i++) {
                LockSupport.park();
                printStr(i);
                LockSupport.unpark(t1);
            }
        });
        t1.start();
        t2.start();

    }

    public static  void printNum(int index){
        System.out.print(a[index]);
    }

    public static void printStr(int index) {
        System.out.print(b[index]);
    }
}
