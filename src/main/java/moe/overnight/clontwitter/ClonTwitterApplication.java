package moe.overnight.clontwitter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(
		info = @Info(
				title = "ClonTwitter API",
				version = "v1",
				description = "SNS 클론 API 명세서"
		)
)
@SpringBootApplication
@EnableJpaAuditing
public class ClonTwitterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClonTwitterApplication.class, args);
	}

}
