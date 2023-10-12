package porori.backend.global.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
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
