package com.example.anton.syshelper;

public class ServerInfo {
    private String title;
    private String name;
    private String host;
    private  String port;
    private String password;

    public ServerInfo(String name, String host, String port, String password,String title) {
        this.name = name;
        this.host = host;
        this.title=title;
        this.port = port;
        this.password = password;
    }

    public ServerInfo() {
        this.name = new String();
        this.host = new String();
        this.title=new String();
        this.port = new String();
        this.password = new String();
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }
}
