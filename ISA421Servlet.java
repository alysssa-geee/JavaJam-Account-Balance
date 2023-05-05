package com.bryant.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ISA421Servlet extends HttpServlet {
	public abstract void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException,IOException; 
	
	public abstract void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException,IOException; 
	
	protected void dispatchNext(HttpServletRequest request, HttpServletResponse response, String next, String applicationContextName)
			throws ServletException, IOException {
		// We can tell the difference between not having a valid next in chain
		// and a dispatch failure. So, we will handle the two conditions separately.
		if (!next.equals("")) {
			if (next.toLowerCase().endsWith(".htm") || next.toLowerCase().endsWith(".html")) {
					response.sendRedirect(request.getScheme()+"://" + request.getHeader("host") + "/" + next);
			} else {
				RequestDispatcher dispatch = getServletContext().getRequestDispatcher(next); 
				// RequestDispatcher dispatch = getServletContext(applicationContextName.getRequestDispatcher(next);
				dispatch.forward(request, response);
			}
		}
	}
	
	private ServletContext getServletContext( String applicationContextName) {
		if (null == applicationContextName || applicationContextName.length() < 1) {
			return getServletContext();
		} else {
			return getServletContext().getContext(applicationContextName);
		}
	}
	
}
