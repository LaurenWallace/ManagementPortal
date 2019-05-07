package com.resort.testing;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import static org.mockito.Mockito.when;

import com.resort.dao.ReimburseDAO;
import com.resort.dao.ReimburseDAOImpl;
import com.resort.dao.UserDAO;
import com.resort.dao.UserDAOImpl;
import com.resort.pojos.Reimbursement;
import com.resort.pojos.User;
import com.resort.service.Service;

public class ServiceTest
{
	@Mock
	private Service service;
	@Mock
	private UserDAOImpl uDAO;
	@Mock
	private ReimburseDAOImpl rDAO;
	
	private User utest;
	private User utest2;
	
	private Reimbursement reim, reim2, reim3;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		HashMap<Integer, String> map = new HashMap<Integer,String>();
		map.put(1, "example@gmail.com");
		map.put(2, "test");
		when(uDAO.getEmails()).thenReturn(map);
		
		
		utest = new User();
		utest.setEmail("koko@gmail.com");
		utest.setFirstname("john");
		utest.setLastname("mon");
		utest.setPassword("pass");
		utest.setIsManager(0);
		utest.setUserid(5);
		when(uDAO.getUserById(5)).thenReturn(utest);
		
		reim = new Reimbursement();
		reim.setRequesterId(5);
		reim.setAmount(12.25);
		reim.setReason("Had to pay for parking");
		reim.setStatName("Pending");
		reim.setTypeName("Travel");
		
		reim2 = new Reimbursement();
		reim2.setRequesterId(5);
		reim2.setAmount(14.12);
		reim2.setReason("Had to pay for parking");
		reim2.setStatName("Pending");
		reim2.setTypeName("Travel");
		
		reim3 = new Reimbursement();
		reim3.setRequesterId(5);
		reim3.setAmount(350.40);
		reim3.setReason("Had to pay for getting injured on the job");
		reim3.setStatName("Pending");
		reim3.setTypeName("Medical");
		ArrayList<Reimbursement> reims = new ArrayList<Reimbursement>();
		reims.add(reim);
		reims.add(reim2);
		reims.add(reim3);
		when(rDAO.getUserReimbursements(5)).thenReturn(reims);
		
		utest2 = new User();
		utest2.setFirstname("mars");
		utest2.setLastname("solar");
		utest2.setEmail("mars@rover.com");
		utest2.setPassword("planets");
		//when(uDAO.addUser("mars", "solar", "mars@rover.com", "planets")).thenReturn(7);
		
		service = new Service(uDAO,rDAO);
	}
	
	//this is successful
	@Test
	public void validatevalidUserTest() 
	{
		assert(service.validateUser("example@gmail.com")==1);
	}
	
	//this is successful
	@Test
	public void validateinvalidUserTest() 
	{
		assert(service.validateUser("ko@gmail.com")==-1);
	}
	
	@Test
	public void validateloginUserTest() 
	{
		assert(service.login(5, "pass").equals(utest));
	}
	
	@Test
	public void validateInvalidLoginPassUserTest() 
	{
		assert(service.login(5, "") == null);
	}
	
	@Test
	public void validateAddUserTest()
	{
		//User utest3 = service.addUser(utest2);
		//assert(utest3.getUserid() == 7);
	}
	
	@Test
	public void validateInvalidAddUserTest() 
	{
		User utest4 = new User();
		utest4.setEmail("");
		utest4.setFirstname("");
		utest4.setLastname("");
		utest4.setPassword("");
		//User utest5 = service.addUser(utest4);
		//assert(utest5 == null);
	}
	
	@Test
	public void validateGetUserReimbursementsTest() {
		reim = new Reimbursement();
		reim.setRequesterId(5);
		reim.setAmount(12.25);
		reim.setReason("Had to pay for parking");
		reim.setStatName("Pending");
		reim.setTypeName("Travel");
		
		reim2 = new Reimbursement();
		reim2.setRequesterId(5);
		reim2.setAmount(14.12);
		reim2.setReason("Had to pay for parking");
		reim2.setStatName("Pending");
		reim2.setTypeName("Travel");
		
		reim3 = new Reimbursement();
		reim3.setRequesterId(5);
		reim3.setAmount(350.40);
		reim3.setReason("Had to pay for getting injured on the job");
		reim3.setStatName("Pending");
		reim3.setTypeName("Medical");
		ArrayList<Reimbursement> reims = new ArrayList<Reimbursement>();
		reims.add(reim);
		reims.add(reim2);
		reims.add(reim3);
		
		assert(service.getUserReimbursements(utest, utest.getUserid()) == reims);
	}

}