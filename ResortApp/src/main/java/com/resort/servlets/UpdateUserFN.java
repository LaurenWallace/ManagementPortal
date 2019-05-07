package com.resort.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.resort.dao.ReimburseDAOImpl;
import com.resort.dao.UserDAOImpl;
import com.resort.pojos.User;
import com.resort.service.Service;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/updateUserFN")
public class UpdateUserFN extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserFN() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAOImpl udao = new UserDAOImpl();
		ReimburseDAOImpl rdao = new ReimburseDAOImpl();
		Service serv = new Service(udao, rdao);
		HttpSession session = request.getSession(false);
		User temp = (User) session.getAttribute("resort_user");
		
		String fn = request.getParameter("firstname");
		serv.updateUserFN(fn, temp.getUserid());
		User up = serv.getUser(temp.getUserid());
		
		if(temp.getIsManager() == 0) {
			RequestDispatcher rds = request.getRequestDispatcher("employeeHomePage.html");
			session.setAttribute("resort_user", up);
			rds.forward(request, response);
		}
		else {
			RequestDispatcher rds = request.getRequestDispatcher("managerHomePage.html");
			session.setAttribute("resort_user", up);
			rds.forward(request, response);
		}
	}

}
