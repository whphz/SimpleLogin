package com.whp.SimpleLogin.util;


import io.jsonwebtoken.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Token {
    /**
     *
     * @param playerName
     * @return token 获取一个5分钟时限的token
     */
    public static String getToken(String playerName){
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("player",playerName);
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.MINUTE,5);
        Date leasttime = calendar.getTime();
        String token=Jwts.builder()
                .setClaims(hashMap)
                .setIssuedAt(new Date())
                .setExpiration(leasttime)
                .setAudience("whphz")
                .signWith(SignatureAlgorithm.HS256,"HHHHHHZ")
                .compact();
        return token;
    }

    public static boolean isOverTime(String token){
        try
        {
            Claims body = Jwts.parserBuilder().setSigningKey("HHHHHHZ").build().parseClaimsJws(token).getBody();
            body.get("palyer").toString();
        }catch (JwtException e)
        {
            return true;
        }
        return false;
    }

}
