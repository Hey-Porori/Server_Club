package porori.backend.global.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@SecurityScheme(
        type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER,
        name = "Authorization", description = "Authorization"
)
@OpenAPIDefinition(security = { @SecurityRequirement(name = "Authorization") })
public class OpenApiConfig {

    @Value("${service.club}")
    private String clubUrl;

    @Bean
    public OpenAPI openAPI() {

        Server devServer = new Server();
        devServer.setDescription("dev");
        devServer.setUrl("http://" + clubUrl);

        Server localServer = new Server();
        localServer.setDescription("local");
        localServer.setUrl("http://localhost:8080");

        return new OpenAPI()
                .info(getInfo())
                .servers(Arrays.asList(devServer, localServer));
    }

    private Info getInfo() {
        return new Info()
                .title("Hey-Porori CLUB API")
                .description("Hey-Porori CLUB API DOCS");
    }

}
