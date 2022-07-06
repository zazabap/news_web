package com.example.demospring1;

public class User {
    private Long uid;
    private String email;
    private String password;
    private String name;

    public User(Long uid, String s, String password, String alice) {
        this.uid = uid ;
        this.email = s;
        this.password = password;
        this.name = alice;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public long getId() {
        return this.uid;
    }
}
