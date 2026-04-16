package com.example.configclient;

import lombok.Data;

@Data
public class Config {
    private String hashId;
    private String dbName;
    private String dbType;
    private String userName;
    private String password;
    private String host;
}
