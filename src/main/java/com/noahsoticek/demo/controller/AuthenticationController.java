package com.noahsoticek.demo.controller;

import com.noahsoticek.demo.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.xml.bind.DatatypeConverter;
import java.util.HashMap;

public class AuthenticationController {

    public static int getPlayerId(String jwt) {
        // Get player & game ID from Token and return them

        String SECRET_KEY = "8Zz5tw0Ionm3XPZZfN0NOml3z9FMfmpgXwovR9fp6ryDIoGRM8EPHAB6iHsc0fb";
        int playerId = 0;

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .parseClaimsJws(jwt).getBody();

            playerId = Integer.parseInt(claims.getSubject());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return playerId;
    }
}
