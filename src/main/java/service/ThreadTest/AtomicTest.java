package service.ThreadTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
    int  a =10;
    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(10);
        AtomicTest a = new AtomicTest();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 200,
                60, TimeUnit.SECONDS, new ArrayBlockingQueue(20));

        for (int j = 0;j<10;j++){
            threadPoolExecutor.execute(()->{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int c = i.getAndDecrement();
                System.out.println("购买了一张票"+c);
            });
        }

        for (int b = 0;b<10;b++){
            threadPoolExecutor.execute(()->{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a.a--;
                System.out.println("#####################"+a.a);
            });
        }


    }
}
