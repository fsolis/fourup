package edu.cst438.fourup;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import java.net.UnknownHostException;

import com.google.gson.Gson;

import com.mongodb.*;

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
				//1. pull data from database
				DBObject tobj = cursor.next();
		        String tempEmail = (String)tobj.get("email");
		        String tempPassword = (String)tobj.get("hpass");
				//2. verify password is correct
				//3. set session to logged in
				//4. put login info inside cookie for return visits
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