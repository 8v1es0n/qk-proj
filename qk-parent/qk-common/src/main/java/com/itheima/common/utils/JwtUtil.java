package com.itheima.common.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {
    // 签名   用于生成和验证JWT令牌
    private static final String SECRET_KEY = "f4lngW0er";

    // jWT令牌过期时间    单位是毫秒
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 8;


    /**
     * 生成令牌
     * @param claims    需要保存到令牌中的数据
     * @return      返回生成的JWT令牌
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()   // 创建JWT构建器
                // 把map中的数据保存到JWT中
                .setClaims(claims)
                // 使用密钥生成签名
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
                // 设置JWT过期时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                // 生成最终JWT令牌
                .compact();
    }

    /**
     * 解析JWT令牌
     * @param token 前端传过来的JWT字符串
     * @return JWT中保存的数据
     */
    public static Claims parseToken(String token) {
        return Jwts.parser() // 创建JWT解析器

                // 设置验证签名时使用的密钥
                .setSigningKey(SECRET_KEY.getBytes())

                // 解析JWT，同时验证签名和过期时间
                .parseClaimsJws(token)

                // 获取JWT中保存的数据
                .getBody();
    }
}
