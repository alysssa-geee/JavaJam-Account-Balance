package com.bryant.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bryant.billing.AccountManager;
import com.bryant.billing.Customer;

@WebServlet("/Account")
public class AccountServlet extends ISA421Servlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		    AccountManager acctMgr = new AccountManager(request, response);
		    acctMgr.execute();
		    System.out.println("Inside Servlet..."+request.getParameter("user"));
		    dispatchNext(request, response, "/response.jsp", "");
		    
		} catch (SQLException e) {
		    request.setAttribute("errorMsg", "An error occurred while accessing the database: " + e.getMessage());
		    dispatchNext(request, response, "/error.jsp", "");
		} catch (ClassNotFoundException e) {
		    request.setAttribute("errorMsg", "An error occurred while accessing the database: " + e.getMessage());
		    dispatchNext(request, response, "/error.jsp", "");
		} catch (Exception e) {
		    request.setAttribute("errorMsg", "An unknown error occurred: " + e.getMessage());
		    dispatchNext(request, response, "/error.jsp", "");
		}

	}
}
