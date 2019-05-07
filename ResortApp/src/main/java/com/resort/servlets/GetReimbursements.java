package com.resort.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.resort.dao.ReimburseDAOImpl;
import com.resort.dao.UserDAOImpl;
import com.resort.dto.EmpDTO;
import com.resort.pojos.Reimbursement;
import com.resort.pojos.User;
import com.resort.service.Service;

/**
 * Servlet implementation class GetReimbursements
 */
@WebServlet("/getReimbursements")
public class GetReimbursements extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetReimbursements() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDAOImpl udao = new UserDAOImpl();
		ReimburseDAOImpl rdao = new ReimburseDAOImpl();
		Service serv = new Service(udao, rdao);
		HttpSession session = request.getSession(false);
		User temp = (User) session.getAttribute("resort_user");
		
		ArrayList<Reimbursement> reims = serv.getUserReimbursements(temp, temp.getUserid());

 		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
 		
 		EmpDTO dto = new EmpDTO(temp, reims);
		String json = ow.writeValueAsString(dto);
		
    	PrintWriter pw = response.getWriter();
    	response.setContentType("application/json");
    	pw.write(json);
    	pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
