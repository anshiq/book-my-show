package com.example.configclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "config-client", url = "${config.server.url:http://localhost:8888}")
public interface ConfigClient {

    @GetMapping("/config/{dbType}/{hashId}")
    Config getByDbTypeAndHashId(@PathVariable("dbType") String dbType, @PathVariable("hashId") String hashId);
}
