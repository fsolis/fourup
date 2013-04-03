package edu.cst438.fourup;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class signup extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Use "request" to read incoming HTTP headers (e.g. cookies)
		// and HTML form data (e.g. data the user entered and submitted)

		// Use "response" to specify the HTTP response line and headers
		// (e.g. specifying the content type, setting cookies).

		PrintWriter out = response.getWriter();
		// Use "out" to send content to browser
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Map<String, String> myResponse = new HashMap<String, String>();
		myResponse.put("Status ", "Success");
		myResponse.put("message", "what happened here");
		myResponse.put("Error", "there was an error");
		String strResponse = new Gson().toJson(myResponse);
		// check data exists DB that it exists;
		PrintWriter out = response.getWriter();
		out.write(strResponse);
		
	}
}