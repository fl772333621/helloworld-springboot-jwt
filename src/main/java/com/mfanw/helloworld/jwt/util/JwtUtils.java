package com.mfanw.helloworld.jwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;

import java.util.Calendar;
import java.util.Date;

public class JwtUtils {
    /**
     * 签发对象：这个用户的id
     * 签发时间：现在
     * 有效时间：30分钟
     * 载荷内容：暂时设计为：这个人的名字，这个人的昵称
     * 加密密钥：这个人的id加上一串字符串
     */
    public static String createToken(String userId, String username, String password) {

        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 30);
        Date expiresDate = nowTime.getTime();

        return JWT.create().withAudience(userId)
                //发行时间
                .withIssuedAt(new Date())
                //有效时间
                .withExpiresAt(expiresDate)
                //载荷，随便写几个都可以
                .withClaim("userName", username)
                .withClaim("realName", password)
                //加密
                .sign(Algorithm.HMAC256(userId + "HelloJwt"));
    }

    /**
     * 检验合法性，其中secret参数就应该传入的是用户的id
     *
     * @param token
     */
    public static void verifyToken(String token, String secret) throws Exception {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret + "HelloLehr")).build();
            verifier.verify(token);
        } catch (Exception e) {
            //效验失败，抛出异常
            throw new Exception("校验失败");
        }
    }

    /**
     * 获取签发对象
     */
    public static String getAudience(String token) throws Exception {
        String audience = null;
        try {
            audience = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            //效验失败，抛出异常
            throw new Exception("校验失败");
        }
        return audience;
    }


    /**
     * 通过载荷名字获取载荷的值
     */
    public static Claim getClaimByName(String token, String name) {
        return JWT.decode(token).getClaim(name);
    }
}
