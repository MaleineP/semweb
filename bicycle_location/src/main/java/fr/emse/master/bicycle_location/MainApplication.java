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
		String query_path = "src/main/resources/queries/static/";   // location of the queries for static data
		String ttl_path = "src/main/resources/ttl/static/";			// location of the queries for dynamic data
		String[] cities = {"st_etienne"};                     // List of the cities which need to load static data
		
		 for(int i=0; i<cities.length; i++) { 	// for all cities
			 File tmpFile = new File(ttl_path+cities[i]+".ttl"); 
			 if (tmpFile.exists() == false)  {			// if the file does not already exist
				 new create_ttl("src/main/resources/sparql-generate.jar", query_path+cities[i]+".sparql", ttl_path+cities[i]+".ttl"); // create the ttl file associated with the city
			 }
		 }
	            
		 // launch the web page
		SpringApplication.run(MainApplication.class, args);
	}

}
