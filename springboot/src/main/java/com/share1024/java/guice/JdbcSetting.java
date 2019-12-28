package com.share1024.java.guice;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2019-12-28
 */
public class JdbcSetting {

    private String username;

    private String password;

    private Integer port;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "JdbcSetting{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", port=" + port +
                '}';
    }
}
