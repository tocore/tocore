package oasis.core.authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import oasis.core.authentication.dto.Member;
import oasis.core.authentication.service.impl.UserAuthService;
import oasis.core.helper.SHA256Helper;


@Component
public class UserAuthenticationProvider implements AuthenticationProvider { //authenticationManager
	
	@Autowired
	private UserAuthService customeUserDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {		
		
		UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication; //유저정보와 이이디비번으으로만든다.(로그인한 유저아이디비번정보를담는다)

		Member member = customeUserDetailsService.loadUserByUsername(authToken.getName()); //UserDetailsService에서 유저정보를 불러온다.
		if (member == null) {
			throw new UsernameNotFoundException(authToken.getName());
		}

		// 패스워드 검증
		if (!matchPassword(member.getSalt(), member.getPassword(), authToken.getCredentials())) {
			throw new BadCredentialsException("not matching username or password");
		}

		@SuppressWarnings("unchecked")
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) member.getAuthorities(); // 유저마다 가진권한을 읽어와야하기떄문에 

		return new UsernamePasswordAuthenticationToken(member, null, authorities);
	}
	
	// 패스워드 salt 처리
	/**
	 * @param salt			: 디비에 저장된 보안용 문자열 - 랜덤으로 생성됨
	 * @param password		: 디비상 비밀번호
	 * @param credentials 	: 사용자로 부터 입력받는 패스워드 
	 * @return				: 일치여부
	 */
	private boolean matchPassword(String salt, String password, Object credentials) {
		
		String encryptedPassword = "";		
		try {			
			encryptedPassword = SHA256Helper.encrypt(salt + ((String)credentials));
		} catch (Exception e) {
			return false;
		} 
		
		return password.equals(encryptedPassword);		
	}
		
	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
