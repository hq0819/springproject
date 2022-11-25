package test;


import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class Demo{

    public static void main(String[] args) {
        String str ="{}{}{}|{}";
        log.info(str,1,2,3,4);
    }

}


/*
You have three positive integers represented as string s1, s2 and s3. you need to return their sum as string.
Example 1:
input: s1 = "5", s2 = "6", s3 = "7"
output: "18"
Example 2:
input: s1 = "1000", s2 = "10", s3 = "1"
output: "1011"
Example 3:
input: s1 = "9999999999999999", s2 = "1", s3 = "1"
output: "10000000000000001"
Constraints:
1 <= s1.length, s2.length, s3.length <= 100
do not use other library(eg. BigInteger)
*/

// 必须定义 `ShowMeBug` 入口类和 `public static void main(String[] args)` 入口方法
 class ShowMeBug {
  public static void main(String[] args) {
    System.out.println(sum("5", "6", "7"));
    System.out.println(sum("1000", "10", "1"));
    System.out.println(sum("9999999999999999", "1", "1"));
  }
  public static String sum(String s1, String s2 , String s3) {
    //todo
      return new BigDecimal(s1).add(new BigDecimal(s2)).add(new BigDecimal(s3)).toString();
  }
}