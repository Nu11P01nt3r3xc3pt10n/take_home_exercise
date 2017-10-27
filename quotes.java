package quotes;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
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


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Long tmp_price = null;
		String price = new String();
		JSONObject tmp = new JSONObject();

		if((request.getParameter("pickup_postcode")!=null) && (request.getParameter("delivery_postcode")!=null)) {
			quotes_services quotes_library = new quotes_services ();
			tmp_price = quotes_library.do_math(request.getParameter("pickup_postcode"),request.getParameter("delivery_postcode"));

			if(tmp_price <0)
				tmp_price *=-1;

		}


		seeker _local_seeker = new seeker();

		JSONArray tmp_carriers = null;
		if(request.getParameter("vehicle")!=null) {

			tmp_carriers = new JSONArray(); 
			tmp_carriers = _local_seeker.retrieve_carriers(request.getParameter("vehicle"),tmp_price);
			try {
				tmp.put("vehicle", request.getParameter("vehicle"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		if(tmp_price != null) {
			price = tmp_price.toString().substring(0,3);
			try {

				tmp.put("price", price);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		response.getWriter().append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">");
		response.getWriter().append("<HTML>");
		response.getWriter().append("<HEAD>");
		response.getWriter().append("<TITLE>SHUTL TEST</TITLE>");
		response.getWriter().append("</HEAD>"); 
		response.getWriter().append("<BODY>");
		response.getWriter().append("<H2 ALIGN=\"CENTER\">Search for carrier</H2>"); 
		response.getWriter().append("<FORM ACTION=\"http://localhost:8080/quotes/quotes\" METHOD=\"POST\">");
		response.getWriter().append("<CENTER>");
		response.getWriter().append("<DIV style='width:100%;'>");
		response.getWriter().append("<LABEL style='display:inline-block;width:40%;background-color:rgba(0,0,255,0.3);'>Pick up from:</LABEL>");
		response.getWriter().append("<INPUT style='display:inline-block;width:40%;text-align:center;' TYPE=\"TEXT\" NAME=\"pickup_postcode\" VALUE='AAAAAA'><BR>");
		response.getWriter().append("<LABEL style='display:inline-block;width:40%;background-color:rgba(0,0,255,0.3);'>Deliver to:</LABEL>");
		response.getWriter().append("<INPUT style='display:inline-block;width:40%;text-align:center;' TYPE=\"TEXT\" NAME=\"delivery_postcode\" VALUE='FFFFFF'><BR>");
		response.getWriter().append("<LABEL style='display:inline-block;width:40%;background-color:rgba(0,0,255,0.3);'>Vehicle:</LABEL>");
		response.getWriter().append("<INPUT style='display:inline-block;width:40%;text-align:center;' TYPE=\"TEXT NAME=\"vehicle\" VALUE=\"bicycle\"><BR><BR><BR>");
		response.getWriter().append("<INPUT style='display:inline-block;width:30%;' TYPE='SUBMIT'>"); 
		response.getWriter().append("</DIV>");
		response.getWriter().append("</CENTER>"); 
		response.getWriter().append("</FORM>"); 
		if(tmp_carriers!=null) {
			if(tmp_carriers.length() >0) {
				for(int i =0;i<tmp_carriers.length();i++) {
					try {
						response.getWriter().append("<DIV style='display:block;width:100%;'><LABEL style='display:inline-block;width:25%;background-color:rgba(0,0,255,0.3);'>"+tmp_carriers.getJSONObject(i).getString("service")+"</LABEL>");
						response.getWriter().append("<LABEL style='display:inline-block;width:25%;background-color:rgba(0,0,255,0.3);'>"+tmp_carriers.getJSONObject(i).getString("price")+"$</LABEL>");
						response.getWriter().append("<LABEL style='display:inline-block;width:25%;background-color:rgba(0,0,255,0.3);'>"+tmp_carriers.getJSONObject(i).getString("delivery_time")+"</LABEL></DIV>");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}

		response.getWriter().append("</BODY>"); 
		response.getWriter().append("</HTML>"); 


		//		response.getWriter().append(tmp.toString());





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
