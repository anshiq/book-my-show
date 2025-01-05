package com.bookMyShow.bookmyshow.controller;



import com.bookMyShow.bookmyshow.entity.Config;
import com.bookMyShow.bookmyshow.services.ConfigService_book;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestMapping("/config")
@RequiredArgsConstructor
public class ConfigController {

    private final ConfigService_book configService;

    @GetMapping("/db-type")
    public ResponseEntity<?> get1(@RequestParam String dbType){
        return new ResponseEntity<>(configService.getByDbType(dbType), HttpStatus.OK);
    }

    @GetMapping("/get-by-type-and-hashId")
    public ResponseEntity<?> get2(@RequestParam String dbType,@RequestParam String hashId){
        Config config  = configService.getByDbTypeAndHashId(dbType,hashId);
        System.out.println(config.toString());
        return new ResponseEntity<>(config,HttpStatus.OK);
    }
}
