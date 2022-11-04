package br.com.market.demands;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DemandsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemandsApplication.class, args);
    }

}
