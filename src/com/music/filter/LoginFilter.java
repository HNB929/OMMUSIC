package com.music.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.music.bean.UserModel;

/**
 * 登录过滤器
 * @author HNB
 *
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {
	public void init(FilterConfig fConfig) throws ServletException { }
	
    public LoginFilter() { }
    
	public void destroy() { }

    /**
     * 执行过滤器拦截操作
     */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 1.判断是否有登录
		HttpServletRequest request2 = (HttpServletRequest)request;
		HttpServletResponse response2 = (HttpServletResponse)response;
		UserModel user = (UserModel)request2.getSession().getAttribute("user");
		
		String url=request2.getRequestURI();
		
//		System.out.println("进入过滤器");
//		System.out.println(url);
//		System.out.println(user);
		
		// 设置通过文件
		if(url.endsWith(".jpg") || 
				url.endsWith(".js")  || 
				url.endsWith(".css") || 
				url.endsWith(".png") || 
				url.endsWith(".lrc") || 
				url.endsWith(".mp3") || 
				url.endsWith(".gif") || 
				url.endsWith(".woff") || 
				url.endsWith(".map") || 
				url.endsWith(".woff2") ||
				url.endsWith("head.jsp") || 
				url.endsWith("bottom.jsp") || 
				url.endsWith("user/register") || 
				url.endsWith("user/login") || 
				url.endsWith("getCode") || 
				url.endsWith("codeLogin")
		) {
			chain.doFilter(request, response);
			return ;
		}
		
		if(user==null && !url.endsWith("loginAndRegister.jsp")){
			//返回登录界面
			response2.sendRedirect("http://localhost:8080/OMmusic/front/loginAndRegister.jsp");
			return;
		}
		chain.doFilter(request, response);
	}




}
