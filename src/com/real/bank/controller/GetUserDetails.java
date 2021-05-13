package com.real.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.real.bank.model.Customer;

@WebServlet("/getUser-details")
public class GetUserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * response.setContentType("application/json");
		 * response.setCharacterEncoding("UTF-8");
		 */
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String jsonStr = "";
		HttpSession session = request.getSession(false);
		if(session != null)
		{
			Customer customer = (Customer) session.getAttribute("customer");
			jsonStr = new Gson().toJson(customer);
			System.out.println(jsonStr);
			out.print(jsonStr);
	        out.flush(); 
		}
		else
		{
			System.out.println("User is not logged in");
		}

	}
	
	
}