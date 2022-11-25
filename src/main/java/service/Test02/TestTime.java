package service.Test02;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;

public class TestTime {
    public static void main(String[] args) {
        Multimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("abc", 1);
        multimap.put("abc", 2);
        multimap.put("abc", 3);
        multimap.put("abc", 4);
        multimap.put("abcd", 5);
        multimap.put("abcde", 6);
        Collection<Integer> abc = multimap.get("abc");
        System.out.println(abc);
    }
}
