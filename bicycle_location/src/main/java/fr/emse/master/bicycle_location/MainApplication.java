package fr.emse.master.bicycle_location;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication(scanBasePackages = "fr.emse.master.bicycle_location")
public class MainApplication {
	public static void main(String[] args) {
		String query_path = "src/main/resources/queries/static/";
		String ttl_path = "src/main/resources/ttl/static/";
		String[] cities = {"st_etienne"};
		
		 for(int i=0; i<cities.length; i++) {
			 File tmpFile = new File(ttl_path+cities[i]+".ttl");
			 if (tmpFile.exists() == false)  {
				 new create_ttl("src/main/resources/sparql-generate.jar", query_path+cities[i]+".sparql", ttl_path+cities[i]+".ttl");
			 }
		 }
	            
		
//		new create_ttl("target/classes/sparql-generate.jar", static_queries, static_ttl);
		SpringApplication.run(MainApplication.class, args);
	}

}
