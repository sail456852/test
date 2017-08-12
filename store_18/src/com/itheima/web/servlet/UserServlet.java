package com.itheima.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.itheima.bean.User;
import com.itheima.constant.Constant;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;
import com.itheima.utils.UUIDUtils;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {
	// 跳转到注册页面
	public String registUI(HttpServletRequest request, HttpServletResponse response) {

		return "/jsp/register.jsp";
	}

	// 注册regist
	public String regist(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 获得请求参数,封装成一个User对象
			Map<String, String[]> map = request.getParameterMap();
			User user = new User();
			BeanUtils.populate(user, map);

			//System.out.println(user.toString());

			//封装uid,状态,激活码code
			user.setUid(UUIDUtils.getId());
			user.setState(Constant.USER_NOT_ACTIVE);
			user.setCode(UUIDUtils.getCode());

			// 调用业务,保存用户 (面向接口接口)
			UserService userService = new UserServiceImpl();
			userService.regist(user);
			// 给用户提示 
			request.setAttribute("msg", "恭喜你,注册成功!赶快去邮箱激活...");
			return "/jsp/msg.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "注册失败!");
			return "/jsp/msg.jsp";
		}
	}

	//激活 active

	public String active(HttpServletRequest request, HttpServletResponse response) {

		try {
			//获得请求参数(激活码code)
			String code = request.getParameter("code");

			//调用业务,根据激活码获得user
			UserService userService = new UserServiceImpl();
			User user = userService.findUserByCode(code);

			//判断user,给用户响应 
			if (user != null) {
				//重定向到登录页面
				response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
				return null;
			} else {
				request.setAttribute("msg", "激活失败,请重新激活~");
				return "/jsp/msg.jsp";
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "激活失败,请重新激活~");
			return "/jsp/msg.jsp";
		}
	}

	// 跳转到登录页面
	public String loginUI(HttpServletRequest request, HttpServletResponse response) {

		return "/jsp/login.jsp";
	}

	//登录login
	public String login(HttpServletRequest request, HttpServletResponse response) {
		try {
			//获得请求参数(用户名和密码)
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			System.out.println("username=" + username);
			System.out.println("password=" + password);

			//调用业务,根据用户名和密码获得用户(User对象)
			UserService userService = new UserServiceImpl();
			User user = userService.login(username, password);

			//判断 
			if (user != null) {
				//用户名和密码匹配
				//判断是否激活
				if (user.getState() == Constant.USER_ACTIVED) {
					//判断是否记住用户名
					String rem = request.getParameter("rem");

					Cookie cookie = new Cookie("username", username);
					if (rem != null && "ok".equalsIgnoreCase(rem)) {
						//用户勾选了 
						//设置时长
						cookie.setMaxAge(60 * 60 * 24 * 7);
						//设置路径
						cookie.setPath(request.getContextPath());
					} else {
						//用户没有勾选
						//设置时长
						cookie.setMaxAge(0);
						//设置路径
						cookie.setPath(request.getContextPath());
					}
					response.addCookie(cookie);

					//已经激活, 保存登录状态,重定向到网站首页
					request.getSession().setAttribute("user", user);
					response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
					return null;
				} else {
					//没有激活
					request.setAttribute("msg", "您还没有激活~");
					return "/jsp/msg.jsp";
				}

			} else {
				//用户名和密码不匹配
				request.setAttribute("msg", "用户名和密码不匹配~");
				return "/jsp/login.jsp";
			}

		} catch (Exception e) {
			e.printStackTrace();
			//登录失败
			request.setAttribute("msg", "登录失败...");
			return "/jsp/msg.jsp";
		}
	}

	//注销 logOut
	public String logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//移除session里面的user
		request.getSession().removeAttribute("user");
		//重定向到网站首页
		response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
		return null;
	}

}