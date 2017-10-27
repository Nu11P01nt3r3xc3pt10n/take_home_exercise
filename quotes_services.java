package quotes;

public class quotes_services {
	public Long do_math(String start, String end) {
		
		return Long.valueOf(start, 36) - Long.valueOf(end, 36);

	}
	
	
	public Long add_differences(Long initial_price, String vehicle_type) {
		
		if(vehicle_type.equals("bicycle")) {
			initial_price= initial_price + (initial_price * 10)/100;
		} else if(vehicle_type.equals("motorbike")) {
			initial_price = initial_price + (initial_price * 15)/100;
		} else if(vehicle_type.equals("parcel_car")) {
			initial_price = initial_price + (initial_price * 20)/100;
		}else if(vehicle_type.equals("small_van")) {
			initial_price = initial_price + (initial_price * 30)/100;
		}else if(vehicle_type.equals("large_van")) {
			initial_price = initial_price + (initial_price * 40)/100;
		} 
		return initial_price;
	}
}
