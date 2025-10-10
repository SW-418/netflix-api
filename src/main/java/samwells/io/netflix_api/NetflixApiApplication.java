package samwells.io.netflix_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import samwells.io.netflix_api.config.JwtConfig;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
public class NetflixApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixApiApplication.class, args);
	}

}
