package kelaskoding.restapi.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
//                .apis(RequestHandlerSelectors.any())
//                 Pake .any() kalo mau discan semua & dibikin dokumentasi semua
                .apis(RequestHandlerSelectors.basePackage("kelaskoding.restapi.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        ApiInfo apiInfo = new ApiInfo(
                "Belajar Spring REST-API",
                "Deskripsi belajar spring rest-api",
                "Versi 0.0.1",
                "www.termsofserviceurl.com",
                new Contact("Norman", "www.norman.id", "mail@norman.id"),
                "Apache License",
                "www.urllicense.com",
                Collections.emptyList()
        );
        return apiInfo;
    }

}
