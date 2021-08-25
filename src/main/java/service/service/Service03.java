package service.service;


import javax.annotation.Resource;

public class Service03 {
    @Resource
    private Service01 service01;
    @Resource

    private Service02 service02;

    @Override
    public String toString() {
        return "Service03{" +
                "service01=" + service01 +
                ", service02=" + service02 +
                '}';
    }
}
