package service.Test02;


import chatServer.SPI.Search;

import java.util.ServiceLoader;

public class TestF {
    public static void main(String[] args) {
       // System.out.println(div(1, 0));
       ServiceLoader<Search> load = ServiceLoader.load(Search.class);
        load.forEach(s->{
            s.test();
        });

        

    }

    public static int div(int a,int b){
        try{
            return a/b;
        }catch (Exception e){
            System.out.println(111);
            return 0;
        }
    }

}
