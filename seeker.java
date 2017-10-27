package quotes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class seeker {
	public JSONArray retrieve_carriers(String vehicle,Long price) {


		JSONArray carriers = null;
		try(Stream<String> stream = Files.lines(Paths.get("/Users/morfeo/Desktop/take_home_exercise-master/quotes/src/quotes/carriers.txt"))) {			
			carriers = new JSONArray(stream.collect(Collectors.joining()));
		} catch(IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//add difference to original price based of which vehicle is used
		quotes_services quotes_library = new quotes_services ();
		price = quotes_library.add_differences(price, vehicle);

		JSONArray retv = new JSONArray();
		if(!vehicle.equals("")) {
			for(int i=0;i<carriers.length();i++) {
				JSONObject tmp_company = new JSONObject();

				try {
					tmp_company = carriers.getJSONObject(i);

					for(int j=0;j<tmp_company.getJSONArray("services").length();j++) {
						JSONObject tmp_vehicles = new JSONObject();
						tmp_vehicles = tmp_company.getJSONArray("services").getJSONObject(j);


						for(int k=0;k<tmp_vehicles.getJSONArray("vehicles").length();k++) {
							JSONObject component = null;
							if(tmp_vehicles.getJSONArray("vehicles").getString(k).equals(vehicle)) {
								component = new JSONObject();
								component.put("service",tmp_company.getString("carrier_name"));
								
								
								Long tmp = tmp_company.getInt("base_price")+price;
								component.put("price",tmp/1000000);
								
								component.put("delivery_time",tmp_vehicles.getInt("tmp_vehicles"));
								retv.put(component);

							}

						}

					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		return retv;
	}
}
