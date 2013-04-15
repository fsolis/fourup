package edu.cst438.fourup;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.gson.Gson;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class signup extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Use "request" to read incoming HTTP headers (e.g. cookies)
		// and HTML form data (e.g. data the user entered and submitted)
		// Use "response" to specify the HTTP response line and headers
		// (e.g. specifying the content type, setting cookies).
		PrintWriter out = response.getWriter();
		// Use "out" to send content to browser
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Map<String, String> myResponse = new HashMap<String, String>();
		myResponse.put("Status ", "Success");
		myResponse.put("message", "what happened here");
		myResponse.put("Error", "there was an error");
		String strResponse = new Gson().toJson(myResponse);
		//try {
			//connect to the database
			Mongo mongo=new Mongo("localhost" , 27017);
			//get accounts database
			DB database=mongo.getDB("accounts");
			//BasicDBObject userAccount=new BasicDBObject();
			//DBCursor found = database.find(userAccount);
			//if (found != null)
			//{
				PrintWriter out = response.getWriter();
				out.write("Error");
			//}

		//}
		//catch (UnknownHostException e) {
			
		//}
		
	}
}