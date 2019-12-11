package fr.emse.master.bicycle_location;
import java.io.IOException;

public class create_ttl {

	public static void create_ttl(
			String jar_path,
			String inp_path,
			String out_path) 
	{
		try {
			Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"java -jar "+jar_path+" -q "+inp_path+" -o " +out_path+ "\"");
		} catch (IOException e) {
		} 
	}
}
