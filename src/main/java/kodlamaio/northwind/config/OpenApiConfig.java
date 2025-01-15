package kodlamaio.northwind.config;

import org.springdoc.core.GroupedOpenApi;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//bu sınıfı NorthwindApplication.java daki main parantezi dışınada yazabilirdik tek sınıfda olabilirlerdi yani
//Bu sınıf, Springdoc OpenAPI yapılandırması sağlar. Bu yapılandırma, API belgelerinizin belirli yollar için oluşturulmasını sağlar.
//@Configuration anotasyonu, bu sınıfın bir konfigürasyon sınıfı olduğunu belirtir.
//publicApi() metodu, GroupedOpenApi yapılandırmasını tanımlar. Bu, API belgelerinin /api/** yollarını içermesini sağlar.
// OpenApiConfig  ve northwindapp.. Bu iki sınıf, Spring Boot uygulamanızın çalışması ve API belgelerinin oluşturulması için birlikte çalışır
//NorthwindApplication sınıfı, uygulamanızın başlatılmasını sağlar.
//OpenApiConfig sınıfı, Springdoc OpenAPI'yi yapılandırarak API belgelerinin doğru şekilde oluşturulmasını sağlar.
//Bu OpenApiConfig sınıfı, Springdoc OpenAPI'nin yapılandırmasını sağlar. Springdoc OpenAPI, Swagger tabanlı API belgelerini oluşturmak için kullanılan bir araçtır. 
//Bu sınıf, OpenAPI belgelerinizin hangi yolları kapsayacağını belirler. 

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/api/**")  // API yollarınızı burada tanımlayın
                .build();
    }
}

//.group("public"): API belgeleriniz için bir grup adı belirler. Bu grup adı, API belgelerinin düzenlenmesinde kullanılır.
//.pathsToMatch("/api/**"): Bu yapılandırma, API belgelerinin hangi yolları içereceğini belirtir. 
//Burada, /api/** yol desenini kullandığınız için, API belgeleri yalnızca /api/ ile başlayan yolları kapsar.
