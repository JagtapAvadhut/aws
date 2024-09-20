package com.aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AwsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwsApplication.class, args);
    }
//curl --location 'localhost:8080/users'
//	curl --location 'localhost:8080/users/faf4d83e-c1d2-4831-a57b-58fe733f8601'
}
