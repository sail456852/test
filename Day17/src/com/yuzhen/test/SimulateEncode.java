package com.yuzhen.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * Servlet implementation class SimulateEncode
 */
public class SimulateEncode extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Test
	public void test1() throws UnsupportedEncodingException
	{
		String str ="ÖÐÎÄ";
		String encode = URLEncoder.encode(str,"UTF-8");
		System.out.println(encode);
		String decode = URLDecoder.decode(encode,"ISO-8859-1");
		System.out.println(decode);
		// use ISO to encoder again
//		String encode2 = URLEncoder.encode(decode,"ISO-8859-1");
//		System.out.println(encode2);
//		String decode2 = URLDecoder.decode(encode2,"UTF-8");
//		System.out.println(decode2);
		// new easier way 
		String converted = new String (decode.getBytes("ISO-8859-1"),"UTF-8");
		
		System.out.println(converted);
		// only effect post method submitting Chinese character
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getCharacterEncoding(); // only set the encoding in body
		/*
		 *  get method :parameters in request line , can only use he String way
		 *  post method: parameters in request body , can be set in getCharacter 
		 *  encoding() way 
		 */
		
		doGet(req, resp);
	}

	
}
