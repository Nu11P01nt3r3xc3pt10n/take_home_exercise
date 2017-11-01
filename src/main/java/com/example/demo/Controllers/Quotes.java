package com.example.demo.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Models.Quote;
import com.example.demo.utility_folder.ShutlUtilty;


@Controller
public class Quotes {
	@RequestMapping(value="/quotes", method=RequestMethod.POST) 
	public ModelAndView searchQuotes(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("quotes");

		List <String> error = new ArrayList<String>();
		Quote _tmp_quote = new Quote();

		if((!request.getParameter("delivery_code").equals("")) &&
				(request.getParameter("delivery_code") != null)){
			_tmp_quote.setDeliveryPostcode(request.getParameter("delivery_code"));	
		} else {
			error.add("Missing delivery code");
		}

		if(!request.getParameter("pickup_code").equals("") &&
				request.getParameter("pickup_code")	!= null) {
			_tmp_quote.setPickupPostcode(request.getParameter("pickup_code"));	
		} else {

			error.add("Missing pickup code");
		}


		if(!request.getParameter("vehicle").equals("") && 
				(request.getParameter("vehicle") != null)) {
			_tmp_quote.setVehicle(request.getParameter("vehicle"));	
		} else {
			error.add("Missing vehicle");

		}

		ShutlUtilty _local_utility = ShutlUtilty.getInstance();

		mav.addObject("vehicle_list",_local_utility.vehicles);

		if(error.isEmpty()) {
			Long price = Math.abs((Long.valueOf(_tmp_quote.getDeliveryPostcode(), 36) - Long.valueOf(_tmp_quote.getPickupPostcode(), 36))/100000000);
			if(_tmp_quote.getVehicle().equals("bycicle"))
				price = price+((price *10)/100);
			if(_tmp_quote.getVehicle().equals("motorbike"))			
				price= price+((price *15)/100);
			if(_tmp_quote.getVehicle().equals("parcel_car"))
				price= price+((price *20)/100);
			if(_tmp_quote.getVehicle().equals("small_van"))
				price= price+((price *30)/100);
			if(_tmp_quote.getVehicle().equals("large_van"))
				price = price+((price *40)/100);
				
			_tmp_quote.setPrice(price);

			
			_local_utility.readCarrier();
			mav.addObject("solutions",_local_utility.search_solutions(_tmp_quote));
			mav.addObject("from",_tmp_quote.getPickupPostcode());
			mav.addObject("to",_tmp_quote.getDeliveryPostcode());
			mav.addObject("vehicle_selected",_tmp_quote.getVehicle());
		} else {
			mav.addObject("errors",error);

		}


		return mav;	
	}

	@RequestMapping(value="/quotes", method=RequestMethod.GET) 
	public ModelAndView showQuotes(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("quotes");

		ShutlUtilty _local_utility = ShutlUtilty.getInstance();
		mav.addObject("vehicle_list",_local_utility.vehicles);

		return mav;	
	}



}
