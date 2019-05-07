package com.resort.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resort.dao.ReimburseDAO;
import com.resort.dao.ReimburseDAOImpl;
import com.resort.dao.UserDAO;
import com.resort.dao.UserDAOImpl;
import com.resort.pojos.User;
import com.resort.service.Service;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	
	static Service service = new Service(new UserDAOImpl(), new ReimburseDAOImpl());
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		int id = service.validateUser(email);
		
		if(id < 0){
			RequestDispatcher rd = request.getRequestDispatcher("resortLogin.html");
			session.setAttribute("message", "Invalid user");
			rd.forward(request, response); // invalid user
		}
		else{
			User u = service.login(id, pass);
			if(u == null){
				session.setAttribute("message", "Incorrect Password");
				RequestDispatcher rd = request.getRequestDispatcher("resortLogin.html");
				rd.forward(request, response); // password is wrong 
			}
			else{
				//j_session_id stored in a cookie on the client's browser
				System.out.println("converting our user and accounts to dto " + u.toString());
				session.setAttribute("user", u);
				if(u.getIsManager() == 0) {
					RequestDispatcher rd = request.getRequestDispatcher("employeeHomePage.html");
					rd.forward(request, response); // successful login 	
				}
				else {
					RequestDispatcher rd = request.getRequestDispatcher("managerHomePage.html");
					rd.forward(request, response); // successful login 	
				}
			}
		}
	  }
	}