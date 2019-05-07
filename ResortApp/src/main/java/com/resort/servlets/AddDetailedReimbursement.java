package com.resort.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resort.dao.ReimburseDAOImpl;
import com.resort.dao.UserDAOImpl;
import com.resort.pojos.User;
import com.resort.service.Service;

/**
 * Servlet implementation class AddDetailedReimbursement
 */
@WebServlet("/addDetailReimbursement")
public class AddDetailedReimbursement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDetailedReimbursement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		
		System.out.println("Inside of AddDReim!");
		
		String reason  = request.getParameter("freason");
		Double amount  = Double.parseDouble(request.getParameter("famount"));
		String receipt = request.getParameter("freceipt");
		String type = request.getParameter("ftype");
		int rtype = 0;
		System.out.println(type);
		switch(type) {
		case "Travel": rtype = 0;
			           break;
		case "Event Supplies": rtype = 1;
							   break;
		case "Training": rtype = 2;
						 break;
		case "Medical": rtype = 3;
						break;
		}
		
		serv.addFullReimbursement(temp, reason, amount, receipt, rtype);
		RequestDispatcher rds = request.getRequestDispatcher("Reimbursements.html");
		rds.forward(request, response);
	}

}
