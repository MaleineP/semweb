package fr.emse.master.bicycle_location;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication(scanBasePackages = "fr.emse.master.bicycle_location")
public class MainApplication {
	public static void main(String[] args) {
		new create_ttl("target/classes/sparql-generate.jar", "target/classes/Static.sparql", "ttl_static.ttl");
		SpringApplication.run(MainApplication.class, args);
	}

}
