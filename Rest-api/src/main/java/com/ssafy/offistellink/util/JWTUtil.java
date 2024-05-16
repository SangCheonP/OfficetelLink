package com.ssafy.offistellink.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Slf4j
public class JWTUtil {

    @Value("${jwt.salt}")
    private String salt;

    @Value("${jwt.access-token.expiretime}")
    private long accessTokenExpireTime;

    @Value("${jwt.refresh-token.expiretime}")
    private long refreshTokenExpireTime;

    public String createAccessToken(String email) {
        return create(email, "access-token", accessTokenExpireTime);
    }

    //	AccessToken에 비해 유효기간을 길게 설정.
    public String createRefreshToken(String email) {
        return create(email, "refresh-token", refreshTokenExpireTime);
    }


    private String create(String email, String subject, long expireTime) {
        //		Payload 설정 : 생성일 (IssuedAt), 유효기간 (Expiration),
        //		토큰 제목 (Subject), 데이터 (Claim) 등 정보 세팅.
        Claims claims = Jwts.claims()
                .setSubject(subject) // 토큰 제목 ex) access-token, refresh-token
                .setIssuedAt(new Date()) // 생성일
                .setExpiration(new Date(System.currentTimeMillis() + expireTime)); // 유효기간

        // 저장할 data
        claims.put("email", email);

        String jwt = Jwts.builder()
                // Header 설정 : 토큰의 타입, 해쉬 알고리즘 정보 세팅
                .setHeaderParam("typ", "JWT").setClaims(claims)
                // Signature 설정 : secret key를 활용한 암호화
                .signWith(SignatureAlgorithm.ES256, this.generateKey())
                .compact(); // 직렬화 처리.

        return jwt;
    }

    // Signature 설정에 들어갈 key 생성.
    private byte[] generateKey() {
        byte[] key = null;

        try {
            // charset 설정 안하면 사용자 플랫폼의 기본 인코딩 설정으로 인코딩 됨
            key = salt.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) { // 지정한 인코딩이 지원되지 않을 때
            // 로깅이 정보 수준으로 설정되어 있는지 확인
            if (log.isInfoEnabled()) {
                e.printStackTrace();
            } else {
                log.error("Making JWT Key Error ::: {}", e.getMessage());
            }
        }

        return key;
    }

    // 전달 받은 토큰이 제대로 생성된 것인지 확인 하고 문제가 있다면 UnauthorizedException 발생
    public boolean checkToken(String token){
        try{
            // Json Web Signature? 서버에서 인증을 근거로 인증 정보를 서버의 private key 서명 한것을 토큰화 한것
            // setSigningKey : JWS 서명 검증을 위한  secret key 세팅
            // parseClaimsJws : 파싱하여 원본 jws 만들기
            Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(token);
            log.debug("claims: {}",claims);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }
}
