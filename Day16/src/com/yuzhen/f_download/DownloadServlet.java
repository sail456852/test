package com.yuzhen.f_download;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.get the data by name from request submitted form data 
		String name = request.getParameter("name");
		//2.The configure file in web.xml file ? 
		// the answer I got : it not just contains web.xml
		ServletContext context = this.getServletContext();
		//3. get the extension of the submitted data
		String mimeType = context.getMimeType(name);
		//4. set the response  Content type using the extension
		response.setContentType(mimeType);
		//5. get the client user interface
		String userAgent = request.getHeader("user-agenet");
		//6. get the encoded filename , Why? encoded?
		// 
		String filename = DownloadUtils.getName(userAgent, name);
		
		//7. set content-disposition
		response.setHeader("content-disposition", "attachment;filename=" + filename);
		
		//8 . get the input stream, using context? 
		InputStream is = context.getResourceAsStream(name);
		// the file is in WebContent folder.so no path needed 
		ServletOutputStream os = response.getOutputStream();
		// 
		int len = 0;
		byte[] bytes = new byte[1024];
		while((len=is.read(bytes))!= -1)
		{
			os.write(bytes,0,len);
		}
		
		is.close();
		os.close();
			
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
