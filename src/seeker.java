package quotes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONException;
import org.json.JSONObject;

public class seeker {
	public JSONObject retrieve_carriers(String vehicle) {
		
		
		JSONObject carriers = null;
		try(Stream<String> stream = Files.lines(Paths.get("/Users/morfeo/Desktop/take_home_exercise-master/quotes/src/quotes/carriers.txt"))) {			
			carriers = new JSONObject(stream.collect(Collectors.joining()));
        } catch(IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JSONObject retv = new JSONObject();
		
		
		return retv;
	}
}
