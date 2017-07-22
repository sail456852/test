package com.yuzhen.a_request;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class LineServlet
 */
public class LineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Operate on request lines
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Important API
		// request
		String method = request.getMethod();
		System.out.println("The request method is: " + method);

		System.out.println("The context path is: " + request.getContextPath());

		System.out.println("The remote address is: " + request.getRemoteAddr());

		System.out.println("The request URL is: " + request.getRequestURI());

		String protocol = request.getProtocol();
		System.out.println(protocol);

		System.out.println("The query string is: " + request.getQueryString());

		System.out.println("The user interface is: " + request.getHeader("user-agent"));

		/*
		 * user-agent: user's browser identifier referer: used to indicate where
		 * the link is from
		 */

//		request.getIntHeader("User-Agent"); throws an Exception
		
		Enumeration<String> names = request.getHeaderNames();
		while(names.hasMoreElements()){
			String name = names.nextElement();
			System.out.println(name);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
