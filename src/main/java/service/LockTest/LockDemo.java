package service.LockTest;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
     int i =100;
     static int b =100;
    static ReentrantLock lock = new ReentrantLock();
    Semaphore se = new Semaphore(0);


    public static void main(String[] args) {
        LockDemo lockDemo = new LockDemo();
        for (int j = 0 ;j<=100;j++){
            new Thread(()->{
                try {


                    lockDemo.dec();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }


        System.out.println(lockDemo.i+"======="+b);
    }

    public void dec() throws InterruptedException {
        se.acquire();
    //    lock.lock();
        this.i--;
        b--;
        //lock.unlock();
        se.release();

    }
}
