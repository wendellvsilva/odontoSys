package odonto.infra.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SpringDocConfiguration {
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                        .info(new Info()
                                .title("OdontoSys")
                                .description("teste")
                                .contact(new Contact()
                                    .name("Wendell Vinicius")
                                    .email("wendell.vinicius@ufrpe.br"))
                        );   
                                
    }
}