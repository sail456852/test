package com.yuzhen.project;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet handles the data offered by HTML form
 * Servlet implementation class LoginServit
 */
public class LoginServit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * parameters from html page
		 */
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// check username 
		if(username == null){
			System.out.println("Username is null ");
			response.getWriter().println("You have not input any username");
			return;
		}
		// set the user object
		User user = new User();
		user.setUsername(username);
		user.setPassword(password); //possibly null
		// other fields remain unfilled.  

		// calling the other layer to do the job; 
		try {
			UserService us = new UserService();
			boolean exist = us.findUser(user);
			if(exist){
				response.getWriter().println("Login Success!");
			}else{
				response.getWriter().println("Your username or password wrong !");
			}
		} catch (SQLException e) {
			response.getWriter().println("Server inner layers down !");
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
