package com.bookMyShow.bookmyshow.services;

import com.bookMyShow.bookmyshow.dto.ConfigDto;
import com.bookMyShow.bookmyshow.entity.Config;

import java.util.List;

public interface ConfigService_book {
    List<Config> getByDbType(String dbType);
    Config getByDbTypeAndHashId(String dbType,String id);
    Config setConfig(ConfigDto configDto);
}
