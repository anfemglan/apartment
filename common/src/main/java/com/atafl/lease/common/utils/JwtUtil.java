package com.atafl.lease.common.utils;

import com.atafl.lease.common.exception.LeaseException;
import com.atafl.lease.common.result.ResultCodeEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.apache.ibatis.ognl.Token;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static SecretKey secretKey = Keys.hmacShaKeyFor("r7ShcHWDbztHp5fDwJjaYUKXEnCGUb2u".getBytes());

    public static String createToken(long userId, String userName) {
        String jwt = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 365L))//
                .setSubject("LOGIN_USER")
                .claim("userId", userId)
                .claim("userName", userName)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
        return jwt;
    }
    public static Claims parseToken(String token) {

        if(token == null){
            throw new LeaseException(ResultCodeEnum.ADMIN_LOGIN_AUTH);
        }

        try{
            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
            Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
            return claimsJws.getBody();

        } catch (ExpiredJwtException e) {
            throw new LeaseException(ResultCodeEnum.TOKEN_EXPIRED);
        }catch(JwtException e){
            throw new LeaseException(ResultCodeEnum.TOKEN_INVALID);
        }

        //.parseClaimsJws(jwt);
    }
    public static void main(String[] args) {

        System.out.println(createToken(8L, "19175088259"));
    }

}
