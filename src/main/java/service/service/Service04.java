package service.service;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class Service04 {
    public static void main(String[] args) {
        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8),10000,0.05);
        bloomFilter.put("hq");

        boolean hq = bloomFilter.mightContain("11");
        System.out.println(hq);


    }

}
