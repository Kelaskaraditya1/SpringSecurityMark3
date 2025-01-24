package com.StarkIndustries.SpringSecurityMark3.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Component
public class JwtService {

    public String secreteKey="";

    public JwtService(){
        try{
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGenerator.generateKey();
            secreteKey=Base64.getEncoder().encodeToString(sk.getEncoded());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String generateJwtToken(String username){

        Map<String,Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*60*10))
                .and()
                .signWith(getKey())
                .compact();
    }

    public Key getKey(){
        byte byteKey[] = Decoders.BASE64.decode(secreteKey);
        return Keys.hmacShaKeyFor(byteKey);
    }
}
