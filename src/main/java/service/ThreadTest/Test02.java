package service.ThreadTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Test02
 * @Description TODO
 * @Author heqin
 * @Date 2021/8/20 15:52
 * @Version 1.0
 **/
public class Test02 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Condition condition = lock.newCondition();
    }
}
