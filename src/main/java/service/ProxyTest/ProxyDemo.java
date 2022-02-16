package service.ProxyTest;

import java.lang.reflect.Proxy;

public class ProxyDemo implements Demo{


    @Override
    public void seyHello() {
        System.out.println("你好！");
    }

    @Override
    public void read() {
        System.out.println("read");
    }


    public static void main(String[] args) {
        ProxyDemo proxyDemo = new ProxyDemo();
        Demo o = (Demo) Proxy.newProxyInstance(proxyDemo.getClass().getClassLoader(), proxyDemo.getClass().getInterfaces(), (proxy, method, agrs) ->{
            System.out.println(111);
            return method.invoke(proxyDemo, agrs);
        } );
        o.seyHello();
    }


    public static void test(Class<?> clazz) throws InstantiationException, IllegalAccessException {

    }
}
