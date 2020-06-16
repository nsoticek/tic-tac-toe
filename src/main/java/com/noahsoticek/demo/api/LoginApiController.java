package com.noahsoticek.demo.api;

import com.noahsoticek.demo.controller.LoginController;
import com.noahsoticek.demo.models.LoginUser;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class LoginApiController {

    LoginController loginController = new LoginController();

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody LoginUser loginUser) {
        // Login & return JWT token
        return loginController.login(loginUser);
    }
}
