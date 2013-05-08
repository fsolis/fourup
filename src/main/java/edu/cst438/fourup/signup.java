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


public class signup extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String verifypassword = request.getParameter("verifypassword");
		Map<String, String> myResponse = new HashMap<String, String>();
		PrintWriter out = response.getWriter();
		if (email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))//make sure email is properly formatted
		{
			try
			{
					//URI mongoURI = new URI(System.getenv("mongodb://fsolis:mongoapp@linus.mongohq.com:10041/app15095098"));
					//MongoURI mongoURI = new MongoURI(System.getenv("MONGOHQ_URL"));
					//DB db = mongoURI.connectDB(); //instance of databse
					//db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());//authenticates d
					//Set<string> accounts = db.getCollectionName("accounts");
					Mongo mongo = new Mongo("localhost", 27017); //creates new instance of mongo
					DB db = mongo.getDB("fourup"); //gets fourup database
					DBCollection accounts = db.getCollection("accounts"); //creates collection for accounts			
					BasicDBObject query = new BasicDBObject(); //creates a basic object named query
					query.put("email", email); //sets email to email
					DBCursor cursor = accounts.find(query);
					if (cursor.size() > 0) //check if email has already been registered
					{
						myResponse.put("Status", "Error");
						myResponse.put("Error", "Account already exists using this email address.");
					} 
					else //since email doesn't currently exist in DB, go ahead and register user
					{
						if (password.equals(verifypassword)) //check that both of the passwords entered match each other
						{
							BasicDBObject document = new BasicDBObject();
							int salt = getSalt();
							String hpass = passwrdHash(password,salt);
							document.put("email", email);
							document.put("salt", salt);
							document.put("password", hpass); //this is where we need to hash the password
							accounts.insert(document);
							myResponse.put("Status", "Sucess");
							myResponse.put("Sucess", "Account has been Created");
							AccountObject user = new AccountObject(email, hpass);
							//set session
							HttpSession session = request.getSession();
							session.setAttribute("currentUser", email);
							//return cookie
							Cookie cookie = new Cookie("fourupCookie", email); //add the login information here
							response.addCookie(cookie);
							//redirect to homepage
							String message = "this is a test";
							myResponse.put("html", "<html></html>");
							response.setContentType("application/json");
							response.setStatus(HttpServletResponse.SC_OK);
							//response.sendRedirect("index.html"); //should add check to index page for cookie with login information 
						}
						else
						{
							myResponse.put("Status", "Failed");
							myResponse.put("Failed", "Passwords do not match.");
							
						}
					}

			} 
			catch (MongoException e)
			{
					
				out.write(e.getMessage());
			}
		}
		else
		{
			myResponse.put("Status", "Invalid");
			myResponse.put("Invalid", "The email address has not been entered correctly."); //should output error
		}

		String strResponse = new Gson().toJson(myResponse);
		response.getWriter().write(strResponse);
		response.getWriter().close();
	}
	public static String passwrdHash(String password,int salt)
	{
		char c;
		int n;
		String temp = "",temper2 = "";
		for(int i = 0;i<password.length();i++)
		{
			c = password.charAt(i);
			n = c * salt;
			temper2 = Integer.toString(n);
			temp = temp + temper2;
		}
		System.out.println(temp);
		return temp;
	}
	public int getSalt()
	{
		Random generator = new Random();
		int salt;
	
		salt = generator.nextInt(1000);
	
		return salt;
	}

}
