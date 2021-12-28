package oasis.core.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

public class AuthenticationFailureHandler
		implements org.springframework.security.web.authentication.AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request
									  , HttpServletResponse response
									  ,	AuthenticationException exception) throws IOException, ServletException {
		
        String errmsg = "";
        if (exception instanceof BadCredentialsException) {
        	errmsg = "알수없는 계정입니다.";
        } else if (exception instanceof InternalAuthenticationServiceException) {
        	errmsg = "알수없는 계정입니다.";
        } else if (exception instanceof DisabledException) {
        	errmsg = "인증 거부 - 계정 비활성화";
        } else if (exception instanceof CredentialsExpiredException) {
        	errmsg = "인증 거부 - 비밀번호 유효기간 만료";
        } else if (exception instanceof SessionAuthenticationException) {
        	errmsg = "이미 로그인 되어 있습니다. 로그아웃 이후 로그인 하여 주십시요.";
        }
         
		request.setAttribute("loginid", request.getParameter("ID"));
		request.setAttribute("loginpass", request.getParameter("PWD"));
		request.setAttribute("errmsg", errmsg);
		
        request.getRequestDispatcher("/login/fail").forward(request, response);
	}
}
