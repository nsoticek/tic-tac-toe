package com.noahsoticek.demo.controller;

import com.noahsoticek.demo.models.LoginUser;
import com.noahsoticek.demo.models.User;
import com.noahsoticek.demo.repository.UserRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;

public class LoginController {

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    UserRepo userRepo = UserRepo.getInstance();
    String SECRET_KEY = "8Zz5tw0Ionm3XPZZfN0NOml3z9FMfmpgXwovR9fp6ryDIoGRM8EPHAB6iHsc0fb";

    public String login(LoginUser loginUser) {
        // Check username; if username exists get all data from DB; if not create new user in DB
        ArrayList<User> users = userRepo.getUsers();
        boolean isUserExisiting = false;
        String token = null;
        User user = null;

        for (int i = 0; i < users.size(); i++) {
            // Iterate through all users from DB; if both values match, get full user an return true
            if(loginUser.getUsername().equals(users.get(i).getUsername())) {
                user = users.get(i);
                isUserExisiting = true;
            }
        }

        if(!isUserExisiting) {
            // If 'isUserExisting' is false create new user in DB;
            user = new User(users.size(), loginUser.getUsername());
            userRepo.addUser(user);
        }

        // Assign user to a game or create new game and return ID from game
        GameController.gameStart(user);
        // Generate JWT token with two subjects (player ID & Game ID)
        token = generateJWT(user.getPlayerId());

        return token;
    }

    private String generateJWT(int playerId) {
        return Jwts.builder().setSubject(String.valueOf(playerId)).signWith(SignatureAlgorithm.HS256, DatatypeConverter.parseBase64Binary(SECRET_KEY)).compact();
    }
}
