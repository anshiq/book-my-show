package com.example.configclient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class ConfigService {

    private final ConfigClient configClient;

    public Config getConfigById(String dbType, String hashId) throws ExecutionException {
        return configClient.getByDbTypeAndHashId(dbType, hashId);
    }
}
