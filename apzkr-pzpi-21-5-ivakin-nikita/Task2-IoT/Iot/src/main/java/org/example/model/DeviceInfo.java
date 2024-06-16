package org.example.model;

public class DeviceInfo {

    private Integer id;

    private String login;

    private String serverUrlSend;
    private String serverUrlAuth;

    private String token;

    public DeviceInfo() {

    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getServerUrlSend() {
        return serverUrlSend;
    }

    public String getServerUrlAuth() {
        return serverUrlAuth;
    }

    public String getToken() {
        return token;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setServerUrlSend(String serverUrlSend) {
        this.serverUrlSend = serverUrlSend;
    }

    public void setServerUrlAuth(String serverUrlAuth) {
        this.serverUrlAuth = serverUrlAuth;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
