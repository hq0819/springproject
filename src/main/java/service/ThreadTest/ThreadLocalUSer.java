package service.ThreadTest;

public class ThreadLocalUSer {
    public static ThreadLocal<User> threadLocal = new ThreadLocal<>();


    public static void UserInfo(User user){
        threadLocal.set(user);
    }

    public static User getUser(){
        return threadLocal.get();
    }

}
