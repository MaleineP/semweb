package fr.emse.master.bicycle_location;
import java.io.IOException;

public class create_ttl {

	public create_ttl(String jar_path, String inp_path, String out_path) {
		try {
			// execute the generate-sparql.jar for a query located in inp_path
			Process process = Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"java -jar "+jar_path+" -q "+inp_path+" -o " +out_path+ "\"");
			// the console need to be closed manually once the loading is over
		} catch (IOException e) {
		} 
	}
}
