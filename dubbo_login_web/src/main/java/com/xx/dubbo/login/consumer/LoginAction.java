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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xx.dubbo.login.api.LoginService;

@Controller
@RequestMapping("/login")
public class LoginAction {
	
	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login.do")//, method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response , Model model) throws ServletException, IOException {
		String result;
		result = login(request);
//		PrintWriter out = response.getWriter();
//		out.print(result);
//		out.flush();
//		out.close();
//		return "index";
		return result;
	}

	private String login(HttpServletRequest request) {
		String result;
		String _username = request.getParameter("username");
		String _password = request.getParameter("password");
		
		if("true".equals(request.getSession().getAttribute(_username))){
			result = "already login";
			return result;
		}
		result = loginService.loginIn(_username, _password);
		if("login successed".equals(result)){
			request.getSession().setAttribute(_username, "true");
		}
		return result;
	}
	
	@RequestMapping(value = "/loginOther.do",produces = "text/javascript;charset=utf-8")//, method = RequestMethod.POST)
	@ResponseBody
	public String loginOther(HttpServletRequest request, HttpServletResponse response , Model model) throws ServletException, IOException {
		String callback = request.getParameter("callback");

		login(request);
		return callback;
	}


	@RequestMapping(value = "/loginOut.do")//, method = RequestMethod.POST)
	@ResponseBody
	public String loginOut(HttpServletRequest request, HttpServletResponse response , Model model) throws ServletException, IOException {
		String result;
		result = loginOut(request);
		return result;
	}

	private String loginOut(HttpServletRequest request) {
		String result;
		String _username = request.getParameter("username");
		if(request.getSession().getAttribute(_username) != null){
			request.getSession().removeAttribute(_username);
			result = "login out successed";
		}else {
			result = "please login";
		}
		return result;
	}

	@RequestMapping(value = "/loginOutOther.do",produces = "text/javascript;charset=utf-8")//, method = RequestMethod.POST)
	@ResponseBody
	public String loginOutOther(HttpServletRequest request, HttpServletResponse response , Model model) throws ServletException, IOException {
		String callback = request.getParameter("callback");
		loginOut(request);
		return callback;
	}
	
	
	
	@RequestMapping(value = "/verify.do", method = RequestMethod.GET)
	@ResponseBody
	public String verify(HttpServletRequest request, HttpServletResponse response , Model model) throws ServletException, IOException {
		String result;
//		PrintWriter out = response.getWriter();
		String _username = request.getParameter("username");
		if("true".equals(request.getSession().getAttribute(_username))){
//			out.print("already logined");
			result = "already logined";
		}else {
//			out.print("please login");
			result = "please login";
		}
//		out.flush();
//		out.close();

		return result;
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