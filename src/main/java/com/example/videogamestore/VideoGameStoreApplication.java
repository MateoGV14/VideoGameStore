package com.example.videogamestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.videogamestore")
public class VideoGameStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoGameStoreApplication.class, args);
    }

}
