package edu.cst438.fourup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.mongodb.*;

public class saveRecord
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if (!request.getSession(false).equals(null))//if session exists save record
		{
			//get current session info
			HttpSession session = request.getSession(false);
			String user = (String)session.getAttribute("currentUser");
			
			//get form info
			String first = request.getParameter("first");
			String second = request.getParameter("second");
			String third = request.getParameter("third");
			String fourth = request.getParameter("fourth");

			//connect to db
			MongoURI mongoURI = new MongoURI(System.getenv("MONGOHQ_URL"));
			DB db = mongoURI.connectDB(); //instance of databse
			db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());
			DBCollection accounts = db.getCollection("accounts");

			//access record
			BasicDBObject search = new BasicDBObject(); //creates a basic object named query
			search.put("email", user);
			DBCursor cursor = accounts.find(search);
			DBObject query = cursor.next();
			BasicDBObject basicQuery = (BasicDBObject)query;
			String history = (String)query.get("searchHistory");
			
			//add data
			history += first + "\r\n" + second + "\r\n" + third + "\r\n" + fourth + "\r\n";
			basicQuery.append("searchHistory", history);
			accounts.update(search, basicQuery);
		}
	}
}
