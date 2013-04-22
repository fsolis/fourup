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
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.MongoURI;
import java.net.UnknownHostException;
import java.util.Set;
import com.mongodb.DB;
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
		myResponse.put("Status", "Success");
		myResponse.put("message", "what happened here");
		myResponse.put("Error", "there was an error");
		String strResponse = new Gson().toJson(myResponse);
		PrintWriter out = response.getWriter();
		try
		{
				//URI mongoURI = new URI(System.getenv("mongodb://fsolis:mongoapp@linus.mongohq.com:10041/app15095098"));
				MongoURI mongoURI = new MongoURI(System.getenv("MONGOHQ_URL"));
				DB db = mongoURI.connectDB(); //instance of databse
				db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());//authenticates db
				//Set<string> accounts = db.getCollectionName("accounts");
				//Mongo mongo = new Mongo("localhost", 27017); //creates new instance of mongo
				//DB db = mongo.getDB("fourup"); //gets fourup database
				DBCollection accounts = db.getCollection("accounts"); //creates collection for accounts			
				BasicDBObject query = new BasicDBObject(); //creates a basicdboject named query
				query.put("email", email); //sets email to email
				DBCursor cursor = accounts.find(query);
				if (cursor.size() > 0) //check if email has already been registered
				{
					out.write(myResponse.get("Error")); //should output error
				} 
				else //since email doesn't currently exist in DB, go ahead and register user
				{
					BasicDBObject document = new BasicDBObject();
					int salt = getSalt();
					String hpass = passwrdHash(password,salt);
					document.put("email", email);
					document.put("salt", salt);
					document.put("password", hpass);//this is where we need to hash the password
					accounts.insert(document);
					out.write(myResponse.get("Status"));
				}

		} 
		catch (MongoException e)
		{
				e.printStackTrace();
		}

		
	}
	public String passwrdHash(String password,int salt)
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
