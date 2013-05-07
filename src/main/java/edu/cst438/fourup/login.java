package edu.cst438.fourup;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.*;

import com.google.gson.Gson;
import java.net.URI;
import com.mongodb.*;
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
import java.util.Set;

public class login extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Map<String, String> myResponse = new HashMap<String, String>();
		try
		{
			MongoURI mongoURI = new MongoURI(System.getenv("MONGOHQ_URL"));
			DB db = mongoURI.connectDB(); //instance of databse
		        db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());
			//Mongo mongo = new Mongo("localhost", 27017); //creates new instance of mongo
			//DB db = mongo.getDB("fourup"); //gets fourup database
			DBCollection accounts = db.getCollection("accounts");
			BasicDBObject query = new BasicDBObject(); //creates a basic object named query
			query.put("email", email);
			System.out.println("email: " + email);
			DBCursor cursor = accounts.find(query);
			if (cursor.size() > 0) //check if account exists
			{
				//pull data from database
				DBObject tobj = cursor.next();
		        String tempEmail = (String)tobj.get("email");
		        String tempPassword = (String)tobj.get("password");
		        System.out.println("tempEmail: " + tempEmail);
		        int tempSalt = (Integer)tobj.get("salt");
				//verify password is correct
		        String testPassword = signup.passwrdHash(password, tempSalt);
		        if ( testPassword.equals(tempPassword) )
		        {
		        	//set session to logged in
		        	AccountObject user = new AccountObject(email, testPassword);
		        	HttpSession session = request.getSession(true);
					session.setAttribute("currentUser", email);
					session.setAttribute("currentPw", password);
					Cookie cookie = new Cookie("fourupCookie", user.toString()); //add the login information here
					response.addCookie(cookie);
					response.setContentType("application/json");
					response.setStatus(HttpServletResponse.SC_OK);
					myResponse.put("Status", "Sucess");
					myResponse.put("Sucess", "Account has been Created");
					myResponse.put("html", "<span><a href=''>" +  email + "</a></span>");
					//response.sendRedirect("index.html"); //should add check to index page for cookie with login information 
		        }
		        else
		        {
		        	//invalid password error
		        	myResponse.put("Status", "Invalid");
					myResponse.put("Invalid", "The password entered is not correct. Please try again.");
		        }
			}
			else
			{
				myResponse.put("Status", "Error");
				myResponse.put("Error", "User does not exist");
			}
		}
		catch (MongoException e)
		{
				e.printStackTrace();
		}

		String strResponse = new Gson().toJson(myResponse);
		response.getWriter().write(strResponse);
		response.getWriter().close();

	}
}