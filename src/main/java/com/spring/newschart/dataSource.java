package com.spring.newschart;

public class dataSource {
    private String jdbcUrl;
    private String username;
    private String password;
    private String maximumPoolSize;
    private String autoCommit;
    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl =jdbcUrl;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMaximumPoolSize(String maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public void setAutoCommit(String autoCommit) {
        this.autoCommit = autoCommit;
    }
}
