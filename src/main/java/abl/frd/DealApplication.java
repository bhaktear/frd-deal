package abl.frd;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class DealApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DealApplication.class, args);
	}
	/*
	@Bean
	public Client genAiClient() {
		// Automatically picks up GEMINI_API_KEY from the environment
		return new Client();
	}

	@Bean
	public CommandLineRunner startupTest(Client client) {
		return args -> {
			GenerateContentResponse response = client.models.generateContent(
				"gemini-2.5-flash",
				"Hello from Java in VS Code!",
				null
			);
			System.out.println(response.text());
		};
	}
		*/

}
