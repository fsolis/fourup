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
import com.mongodb.MongoURI;
import java.net.UnknownHostException;
import com.mongodb.DB;
import com.mongodb.MongoException;

public class login extends HttpServlet
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
		PrintWriter out = response.getWriter();
		try
		{
			MongoURI mongoURI = new MongoURI(System.getenv("MONGOHQ_URL"));
			DB db = mongoURI.connectDB(); //instance of databse
			DBCollection accounts = db.getCollection("accounts");
			BasicDBObject query = new BasicDBObject(); //creates a basic object named query
			query.put("email", email);
			DBCursor cursor = accounts.find(query);
			if (cursor.size() > 0) //check if account exists
			{
				//1. verify password is correct
				//2. set session to logged in
				//3. put login info inside cookie for return visits
			}
			else
			{
				out.write(myResponse.get("Error")); //should output error
			}
		}
		catch (MongoException e)
		{
				e.printStackTrace();
		}
	}
}