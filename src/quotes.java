package quotes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class quotes
 */





@WebServlet("/quotes")
public class quotes extends HttpServlet implements Servlet {
	

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public quotes() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//		doGet(request, response);
		Long tmp_price = null;
		String price = new String();
		JSONObject tmp = new JSONObject();
		quotes_services quotes_library = new quotes_services ();
		
		if((request.getParameter("pickup_postcode")!=null) && (request.getParameter("delivery_postcode")!=null)) {

			tmp_price = quotes_library.do_math(request.getParameter("pickup_postcode"),request.getParameter("delivery_postcode"));

			if(tmp_price <0)
				tmp_price *=-1;
			try {
				tmp.put("pickup_postcode", request.getParameter("pickup_postcode"));
				tmp.put("delivery_postcode", request.getParameter("delivery_postcode"));

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		
		if(request.getParameter("vehicle")!=null) {
			tmp_price = quotes_library.add_differences(tmp_price, request.getParameter("vehicle"));
			try {
				tmp.put("vehicle", request.getParameter("vehicle"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}



		price = tmp_price.toString().substring(0,3);
		try {
			
			tmp.put("price", price);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		seeker _local_seeker = new seeker();
				
		JSONObject tmp_carriers = _local_seeker.retrieve_carriers("bicycle");
		
		
		response.getWriter().append(tmp.toString());
	}

	
	
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
