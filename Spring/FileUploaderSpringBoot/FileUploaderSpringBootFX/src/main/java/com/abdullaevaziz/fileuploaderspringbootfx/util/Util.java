package com.abdullaevaziz.fileuploaderspringbootfx.util;

import io.jsonwebtoken.Jwts;

import java.util.Base64;

public class Util {

    private static String secretKey = "jwtappsecret";

    public static String getUsername(String token){
        return (String) Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes())).parseClaimsJws(token).getBody().get("username");
    }

    public static String getRole(String token){
        return (String) Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes())).parseClaimsJws(token).getBody().get("role");
    }

    public static long getId(String token){
        return (int) (Integer) Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes())).parseClaimsJws(token).getBody().get("id");
    }
}
