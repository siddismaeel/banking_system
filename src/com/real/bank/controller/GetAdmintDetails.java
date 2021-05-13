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
import com.real.bank.model.Admin;


@WebServlet("/get-admint-details")
public class GetAdmintDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.setContentType("application/json");
		response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String jsonStr = "";
		
		HttpSession session = request.getSession();
		
		if(session != null)
		{
			Admin admin = (Admin) session.getAttribute("admin");
			
			jsonStr = new Gson().toJson(admin);
			System.out.println("In admin session block");
		}
		else
		{
			jsonStr = "{No data found!}";
		}
		
		out.write(jsonStr);
		
	}

}
