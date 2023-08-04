package porori.backend.global.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {

//        Server devServer = new Server();
//        devServer.setDescription("dev");
//        devServer.setUrl("OUR_DOMAIN");

        Server localServer = new Server();
        localServer.setDescription("local");
        localServer.setUrl("http://localhost:8082");

//        return new OpenAPI()
//                .info(getInfo())
//                .servers(Arrays.asList(devServer, localServer));
        return new OpenAPI()
                .info(getInfo());

    }

    private Info getInfo() {
        return new Info()
                .title("Hey-Porori CLUB API")
                .description("Hey-Porori CLUB API DOCS");
    }

}
