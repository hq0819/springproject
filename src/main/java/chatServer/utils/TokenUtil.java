package chatServer.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    public static String getUserToken(Map<String,Object> claims, String privateKey, long expTime){
        JwtBuilder bd = Jwts.builder();
        bd.addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+expTime))
                .signWith(SignatureAlgorithm.HS256,privateKey);
        return bd.compact();
    }
    public static Claims verifyToken(String token, String key){
        JwtParser parser = Jwts.parser();
        return parser.setSigningKey(key).parseClaimsJws(token).getBody();
    }

    public static void main(String[] args) throws InterruptedException {
        HashMap<String, Object> c = new HashMap<>();
        c.put("name","heqin");
        c.put("passwd","12345");
        String cs = TokenUtil.getUserToken(c, "", 1000 * 3);
        System.out.println(cs.length());
        System.out.println(cs);
        Claims heqin = TokenUtil.verifyToken(cs, "");
        System.out.println(heqin);

    }

}
