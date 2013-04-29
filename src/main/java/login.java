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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Map<String, String> myResponse = new HashMap<String, String>();
		myResponse.put("Status ", "Success");
		myResponse.put("Error", "User does not exist.");
		myResponse.put("Invalid", "The password entered is not correct. Please try again.");
		String strResponse = new Gson().toJson(myResponse);
		PrintWriter out = response.getWriter();
		try
		{
			//MongoURI mongoURI = new MongoURI(System.getenv("MONGOHQ_URL"));
			//DB db = mongoURI.connectDB(); //instance of databse
			Mongo mongo = new Mongo("localhost", 27017); //creates new instance of mongo
			DB db = mongo.getDB("fourup"); //gets fourup database
			DBCollection accounts = db.getCollection("accounts");
			BasicDBObject query = new BasicDBObject(); //creates a basic object named query
			query.put("email", email);
			DBCursor cursor = accounts.find(query);
			if (cursor.size() > 0) //check if account exists
			{
				//pull data from database
				DBObject tobj = cursor.curr();
		        String tempEmail = (String)tobj.get("email");
		        String tempPassword = (String)tobj.get("password");
		        int tempSalt = (Integer)tobj.get("salt");
				//verify password is correct
		        String testPassword = signup.passwrdHash(password, tempSalt);
		        if ( testPassword.equals(tempPassword) )
		        {
		        	//set session to logged in
		        	AccountObject user = new AccountObject(email, testPassword);
		        	HttpSession session = request.getSession();
					session.setAttribute("currentUser", email);
					Cookie cookie = new Cookie("fourupCookie", user.toString()); //add the login information here
					response.addCookie(cookie);
					response.sendRedirect("index.html"); //should add check to index page for cookie with login information 
		        }
		        else
		        {
		        	//invalid password error
		        	out.write(myResponse.get("Invalid"));
		        }
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