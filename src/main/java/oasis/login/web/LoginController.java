/*
 * Copyright 2008-2009 the original author or authors.
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
package oasis.login.web;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import egovframework.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import oasis.core.annotation.DescriptionParametes;
import oasis.core.authentication.dto.Member;
import oasis.member.service.MemberService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 * @Class Name : EgovSampleController.java
 * @Description : EgovSample Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Slf4j
@Controller
@DescriptionParametes
public class LoginController extends BaseController {
	
	@Resource(name = "memberService")
	private MemberService memberService;
		
	@RequestMapping(value = "/login/loginform")
	public String login(Locale locale, Model model) {
		return "login/loginform";
	}	
	
	/*
	 * 로그인 실패시 실패 횟숮 증가
	 * */
	@RequestMapping(value = "/login/fail", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView loginFail(ModelAndView mav, HttpServletRequest request) throws Exception {		
		
		String loginid = (String)request.getAttribute("loginid");
		String errmsg = (String)request.getAttribute("errmsg");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("USER_ID", loginid);	
		try {
			// memberService.loginFailCountIncrease(params);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}	
		
		mav.addObject("fail_login_id", loginid);
		mav.addObject("errmsg", errmsg);
		mav.setViewName("login/loginform");
		return mav;
	}
	
	/*
	 * 로그인 실패시 세션 정보 처리
	 * */
	@RequestMapping(value = "/login/logoutSuccess", method = RequestMethod.GET)
	public String logout(HttpServletRequest req) {		
        HttpSession session = req.getSession(false);
        if(session != null) {
        	session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
		return "login/loginform";
	}
		
	/**
	 * 로그인 성공시 세션 정보 처리
	 */
	@RequestMapping(value = "/login/loginSuccess", method = RequestMethod.GET)
	public ModelAndView home(ModelAndView mav, HttpSession session, HttpServletRequest request) {
	  
		Member user = null;		
		try {
			user = (Member)SecurityContextHolder.getContext().getAuthentication().getPrincipal();			
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
			session.setAttribute("userInfo", user);		
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("USER_ID", user.getUserid());		
			// memberService.loginFailCountInitialize(params);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}		
		mav.setViewName("redirect:/home");		
		return mav;
	}
}
