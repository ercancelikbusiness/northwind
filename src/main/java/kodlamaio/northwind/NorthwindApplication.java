package kodlamaio.northwind;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication anotasyonu, üç önemli anotasyonu bir arada sunar: @EnableAutoConfiguration, @ComponentScan ve @Configuration.

public class NorthwindApplication {

    public static void main(String[] args) {
        SpringApplication.run(NorthwindApplication.class, args);
        //spring boot giriş noktasıdır zaten .run metodu ile SpringApplication.run() metodunu çağırarak başlatılır. 
        //Bu metod, Spring Framework'ün yapılandırmasını başlatır ve Spring konteynerini oluşturur.
    }
}
