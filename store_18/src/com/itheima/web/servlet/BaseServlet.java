
package com.itheima.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//1.获得method请求参数
			String methodStr = req.getParameter("method");
			
			//2.获得字节码对象
			Class clazz = this.getClass();
			
			//3.反射对应的method对象
			Method method = clazz.getDeclaredMethod(methodStr, HttpServletRequest.class,HttpServletResponse.class);
			//4.调用
			String path = (String) method.invoke(this, req,resp);
			
			if(path != null){
				//转发
				req.getRequestDispatcher(path).forward(req, resp);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
