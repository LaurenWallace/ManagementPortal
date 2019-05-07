package com.resort.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

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
 * Servlet implementation class UpdateStatus
 */
@WebServlet("/updateStatus")
public class UpdateStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStatus() {
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
		
		System.out.println("Inside Update Status");
		
		User temp = (User) session.getAttribute("resort_user");
		
		Map<String, String[]> myMap = request.getParameterMap();
		Set<String> set = myMap.keySet();
		
		ObjectMapper jackson = new ObjectMapper();
		Object keys = set.toArray()[0];
		
		ArrayList<String> params = jackson.readValue((String)keys, ArrayList.class);
		
		Integer rid  = Integer.parseInt(params.get(0));
		String stat  = params.get(1);
		
		int rstat = 0;
		
		switch(stat) {
		case "Approved": rstat = 1;
						 serv.updateReimbursement(rid, rstat, temp);
						 break;
		case "Denied": rstat = 2;
					   serv.updateReimbursement(rid, rstat, temp);
					   break;
		}
	}

}
