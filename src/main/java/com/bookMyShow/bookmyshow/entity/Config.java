package com.bookMyShow.bookmyshow.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "config")
public class Config {
    @Id
    private String id;
    private String hashId;
    private String dbName;
    private String dbType;
    private String userName;
    private String password;
    private String host;
}
