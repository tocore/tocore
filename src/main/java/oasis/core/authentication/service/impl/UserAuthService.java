package oasis.core.authentication.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import oasis.core.authentication.dto.Member;
import oasis.member.service.impl.MemberMapper;

@Slf4j
@Service
public class UserAuthService implements UserDetailsService {
		
	// TODO mybatis 사용
	@Resource(name="memberMapper")
	private MemberMapper memberMapper;

	// 디비에서 유저정보를 불러오는메소 이것을 AuthenticationProvider에서 인증을통함
	@Override
	public Member loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Member member = null;	
		List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
		
		try {
			Map<String, Object> param = new HashMap<String, Object>();		
			param.put("USER_ID", username);
			member = (Member)memberMapper.selectMember(param);
			
			// 메뉴 권한 할당
			list = memberMapper.selectMenuList(param);			
			member.setMenulist(list);
		} catch (DataAccessException de) {
			log.error(de.getMessage(), de);
			throw new UsernameNotFoundException("Invalid Auth");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new UsernameNotFoundException("Invalid member");
		}
		
		return member;
	}
}
