package com.noahsoticek.demo.repository;

import com.noahsoticek.demo.models.User;

import java.util.ArrayList;

public class UserRepo {

    private ArrayList<User> users = new ArrayList<>();
    private static UserRepo instance;

    public static UserRepo getInstance() {
        if(instance == null) {
            instance = new UserRepo();
            instance.init();
        }
        return instance;
    }

    public boolean addUser(User user) {
        users.add(user);
        return true;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void init() {
        users.add(new User(1, "hubert"));
        users.add(new User(2, "guntram"));
    }
}
