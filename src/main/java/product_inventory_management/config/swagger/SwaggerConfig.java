package product_inventory_management.config.swagger;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "Bearer Authentication",
                                        createAPIKeyScheme()
                                )
                )
                .info(
                        new Info()
                            .title("Product Inventory Management API")
                            .description("Documentação da API de gerenciamento de inventário de produtos")
                            .version("1.0")
                            .contact(
                                    new Contact()
                                            .name("Leonardo Ferreira")
                                            .email( "leonardo.ferreira@jala.university")
                                            .url("leonardo.ferreira@jala.university"))
                            .license(
                                    new License()
                                            .name("Jala University License")
                                            .url("https://jala.university")
                            )
                );
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
