/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xx.dubbo.login.consumer;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xx.dubbo.login.api.LoginService;

@Controller
@RequestMapping("/login")
public class LoginAction {
    
	@RequestMapping(value = "/login.do")//, method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response , Model model) throws ServletException, IOException {

		String _username = request.getParameter("username");
		String _password = request.getParameter("password");
		
		String result = new LoginConsumer().login(_username, _password);
		if("login successed".equals(result)){
			request.getSession().setAttribute("loginState", "true");
		}
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
//		return "index";
	}


	@RequestMapping(value = "/verify.do", method = RequestMethod.GET)
	public void verify(HttpServletRequest request, HttpServletResponse response , Model model) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		if("true".equals(request.getSession().getAttribute("loginState"))){
			out.print("already logined");
		}else {
			out.print("please login");
		}
		out.flush();
		out.close();
		
	}
	
	

    private static LoginService loginService;

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	public void start() throws Exception {
      for (int i = 0; i < Integer.MAX_VALUE; i ++) {
          try {
          	String hello = loginService.loginIn("kingdee", "123456");
              System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " + hello);
          } catch (Exception e) {
              e.printStackTrace();
          }
          Thread.sleep(2000);
      }
		
	}
}