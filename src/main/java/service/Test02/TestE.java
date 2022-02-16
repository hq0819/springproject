package service.Test02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

/**
 * @ClassName TestE
 * @Description TODO
 * @Author heqin
 * @Date 2021/8/24 9:18
 * @Version 1.0
 **/
public class TestE {
    public static void main(String[] args) {
        Student a = new Student("张三", "数学", 23);
        Student b = new Student("李四", "数学", 23);
        Student c = new Student("王五", "数学", 23);
        Student f = new Student("张三", "语文", 23);
        Student g = new Student("李四", "语文", 23);
        Student h = new Student("王五", "语文", 23);
        ArrayList<Student> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(f);
        list.add(g);
        list.add(h);

        HashMap<String, String> collect = list.stream().collect(
                Collectors.toMap(Student::getSubject,
                Student::getName, (k, v) -> k + v,
                HashMap::new));
        System.out.println(collect);


    }
































}
