package edu.cst438.fourup;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.gson.Gson;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
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
		try
		{
				Mongo mongo = new Mongo("localhost", 27017);
				DB db = mongo.getDB("fourup");
				DBCollection userAccounts = db.getCollection("accounts");
				BasicDBObject query = new BasicDBObject();
				query.put("email", email);
				DBCursor cursor = userAccounts.find(query);
				if (cursor.hasNext()) //check if email has already been registered
				{
					PrintWriter out = response.getWriter();
					out.write(myResponse.get(2)); //should output error
				} 
				else //since email doesn't currently exist in DB, go ahead and register user
				{
					BasicDBObject document = new BasicDBObject();
					document.put("email", email);
					document.put("password", password); //this is where we need to hash the password
					PrintWriter out = response.getWriter();
					out.write(myResponse.get(0)); 
					
				} 
		} 
		catch (MongoException e)
		{
				e.printStackTrace();
		}

		
	}
}