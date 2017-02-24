package com.zh.framework.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mrkin on 2016/11/29.
 */
public class JwtUtil {
    /**
     * token 信息
     */
    public static String USERID="userid";
    public static String TYPE="type";
    public static String AUD="aud";

    //存储userid和有效时间的map
    private static Map<String,Long> tokenValidStore=new HashMap<>();
    public static Long appValidTIME=Long.valueOf(ConfigUtil.get("appValidTIME"));
    public static Long webValidTIME=Long.valueOf(ConfigUtil.get("webValidTIME"));
    private static String base64Security=ConfigUtil.get("base64Security");
    public static Claims parseJWT(String jsonWebToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    /**生成token 带有效时间
     * @param userId  用户id
     * @param type  登录类型
     * @param audience 观众
     * @param TTLMillis 有效时间
     * @return
     */
    public static String createJWT(String userId, String type,
                                   String audience, long TTLMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .claim(USERID, userId)
                .claim(TYPE, type)
                .setAudience(audience)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (TTLMillis >= 0) {
            long expMillis = nowMillis + TTLMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }
        //生成JWT
        return builder.compact();
    }

    /** 生成带时间戳
     * @param userId  用户id
     * @param type  登录类型
     * @param audience 观众
     * @return
     */
    public static String createJWT(String userId, String type,
                                   String audience) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//        long nowMillis = System.currentTimeMillis();
//        Date now = new Date(nowMillis);
        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .claim(USERID, userId)
                .claim(TYPE, type)
                .setAudience(audience)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        Date exp = new Date();
        builder.setNotBefore(exp);

        //生成JWT
        if (StaticUtil.LOGIN_TYPE_WEB.equals(type)) {
            tokenValidStore.put(userId+StaticUtil.LOGIN_TYPE_WEB, new Date().getTime() + JwtUtil.webValidTIME);
        }else {
            tokenValidStore.put(userId+StaticUtil.LOGIN_TYPE_APP, new Date().getTime() + JwtUtil.appValidTIME);
        }
        return builder.compact();
    }

    public static Map<String, Long> getTokenValidStore() {
        return tokenValidStore;
    }


}
