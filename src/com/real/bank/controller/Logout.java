package com.real.bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.invalidate();
		Cookie[] cookies = request.getCookies();
		
		/*
		 * for(Cookie cookie: cookies) { cookie.setMaxAge(0);
		 * response.addCookie(cookie); }
		 */
		response.setContentType("text/xml");
		
		//response.getWriter().write("<logout>true</logout>");
		
		request.getRequestDispatcher("/home").forward(request, response);
	}

}
