package com.example.demo.utility_folder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.demo.Models.Quote;

public class ShutlUtilty {

	public static JSONArray carriers = new JSONArray();
	public static List <String> vehicles = new ArrayList <String>();
	private String text_file = new String();
	private static ShutlUtilty istanza = null;

	private ShutlUtilty() {}

	public static synchronized ShutlUtilty getInstance() {
		if (istanza == null) {
			istanza = new ShutlUtilty();
			vehicles.add("bicycle");
			vehicles.add("motorbike");
			vehicles.add("parcel_car");
			vehicles.add("small_van");
			vehicles.add("large_van");
			
			
		}


		return istanza;
	}
	

	public void readCarrier() {
		try {
			readFile();

			carriers = new JSONArray(text_file);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	
	public List <JSONObject> search_solutions(Quote quote) {
		List <JSONObject> _retv = new ArrayList <JSONObject>();
		try {
			for(int carreir_index=0;carreir_index<carriers.length();carreir_index++) {
				JSONObject _tmp_carrier= new JSONObject ();

				_tmp_carrier = carriers.getJSONObject(carreir_index);

				JSONArray carrier_service_list= new JSONArray();
				carrier_service_list=_tmp_carrier.getJSONArray("services"); 
				for(int service_index=0;service_index<carrier_service_list.length();service_index++) {
					JSONObject _tmp_carrier_service= new JSONObject ();

					_tmp_carrier_service = carrier_service_list.getJSONObject(service_index);

					JSONArray vehicle_list = new JSONArray();
					vehicle_list=_tmp_carrier_service.getJSONArray("vehicles");
					for(int vehicle_index=0;vehicle_index<vehicle_list.length();vehicle_index++) {
						if(vehicle_list.getString(vehicle_index).equals(quote.getVehicle())) {
							JSONObject valid_carrier = new JSONObject();
							valid_carrier.put("service", _tmp_carrier.getString("carrier_name"));
							valid_carrier.put("delivery_time", _tmp_carrier_service.getInt("delivery_time"));
							valid_carrier.put("price", _tmp_carrier.getInt("base_price")+quote.getPrice());
//							_retv.put();
							_retv.add(valid_carrier);
							break;
						}
						
					}

				}

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			_retv=null;
		}
		return _retv;
	}

	private void readFile() throws IOException {

		String fileName = "/src/data/carrier_data.json"; // provide an absolute path here to be sure that file is found
		String filePath = new File("").getAbsolutePath();

		BufferedReader reader = new BufferedReader(new FileReader(filePath + fileName));

		try{                           
			String line = null;         
			while ((line = reader.readLine()) != null){
				if (!(line.startsWith("*"))){
					text_file+=line;
				}
			}               
		}catch (IOException ex){
			ex.printStackTrace();
		}finally{
			reader.close();
		} 


	}
}