package service.ThreadTest;

public class ThreadLocalTest {
    public static void main(String[] args) {
        new Thread(()->{
            ThreadLocalUSer.UserInfo(new User("zs","sdf"));
            User user = ThreadLocalUSer.getUser();
            System.out.println(user);
        }).start();

        new Thread(()->{
            ThreadLocalUSer.UserInfo(new User("ls","1231"));
            User user = ThreadLocalUSer.getUser();
            System.out.println(user);
        }).start();
    }
}
