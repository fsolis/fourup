package edu.cst438.fourup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import com.google.gson.Gson;
import com.mongodb.*;

import java.io.PrintWriter;
import javax.servlet.http.*;
import java.util.*;

import java.net.URI;
import java.net.UnknownHostException;

import java.util.Set;

public class saveRecord extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Map<String, String> myResponse = new HashMap<String, String>();
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
			// MongoURI mongoURI = new MongoURI(System.getenv("MONGOHQ_URL"));
			// DB db = mongoURI.connectDB(); //instance of databse
			// db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());
			// DBCollection accounts = db.getCollection("accounts");

			//connect to local db
			Mongo mongo = new Mongo("localhost", 27017); //creates new instance of mongo
			DB db = mongo.getDB("fourup"); //gets fourup database
			DBCollection accounts = db.getCollection("accounts");

			//access record
			// BasicDBObject search = new BasicDBObject(); //creates a basic object named query
			// search.put("email", user);
			// DBCursor cursor = accounts.find(search);
			// DBObject query = cursor.next();
			// BasicDBObject basicQuery = (BasicDBObject)query;
			// String history = (String)query.get("searchHistory");
			
			BasicDBObject docToInsert = new BasicDBObject("firstLink", first);
			docToInsert.put("secondLink", second);
			docToInsert.put("thirdLink", third);
			docToInsert.put("fourthLink", fourth);

			BasicDBObject updateQuery = new BasicDBObject("email", user);

			BasicDBObject updateCommand = new BasicDBObject("$push", new BasicDBObject("savedLinks", docToInsert));
			accounts.update(updateQuery,updateCommand);
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_OK);
			myResponse.put("updated", docToInsert.toString());
			
			
		} else {
			myResponse.put("html", "<html></html>");
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
		}

		String strResponse = new Gson().toJson(myResponse);
		response.getWriter().write(strResponse);
		response.getWriter().close();
	}
}
