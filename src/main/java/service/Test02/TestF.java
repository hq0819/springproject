package service.Test02;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;



public class TestF {
    public static void main(String[] args) {
        List<Integer> list = Stream.of(Arrays.asList(1),Arrays.asList(2))
                .flatMap(stream -> stream.stream())
                .collect(Collectors.toList());


    }
}
