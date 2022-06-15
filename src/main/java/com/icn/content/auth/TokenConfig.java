package com.icn.content.auth;

//import com.icn.content.user.UserEntity;
//import com.icn.content.user.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class TokenConfig {
    @Value("${security.oauth2.resource.jwt.key-value}")
    private String secretKey;

//    @Autowired
//    UserService userService;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

//    public UserEntity getUserInformation(String token){
//        String username = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
//        return userService.findByUsername(username);
//    }

    public String getUserName(String token){
        String username = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        return username;
    }

    public String getKoreanName(String token){
        String koreanName = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("korean_name").toString();
        return koreanName;
    }
}
