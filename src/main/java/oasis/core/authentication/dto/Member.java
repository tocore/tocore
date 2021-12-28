package oasis.core.authentication.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Member implements UserDetails{

	private static final long serialVersionUID = 1L;
	private String userid;// id
	private String username;// 이름	
	private String password;// 패스워드
	private boolean enabled;// 계정사용가능여부
	private String age;// 나이
	private String clientIp;// 나이
	private String salt;//패스워드 salt, 패스워드 등록시 같이 등록되게 한다.

	private List<Map<String, Object>> menulist;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		if (username != null && username.equals("admin")) {
			authorities.add(new SimpleGrantedAuthority("USER_MANAGER"));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		// 계정 만료 여부를 처리
		// 디비 접속 만효 여부 체크 해서 리턴
		
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		// 계정이 잠겨있는지 판별해서 리턴
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		// 계정이 만효 되었는지 판별해서 리턴
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		
		// 계정이 사용가능한 계정인지 체크 해서 리턴
		return this.enabled;
	}
	
	/*
	 * 주의사항으로는 Spring Security에서 제공하는 Principal, UserDetails interface를 구현하는 객체는 
	 * equals와 hashcode를 반드시 override하셔야 중복 로그인 방지 처리가 가능해집니다.
	 * */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;      
        
        // 동일 유저인지 체크
        Member user = (Member) obj;        
        return userid.equals(user.getUserid()) && username.equals(user.getUsername());
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(userid, username, password, clientIp);
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	
	public List<Map<String, Object>> getMenulist() {
		return menulist;
	}

	public String getSalt() {
		return salt;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public void setMenulist(List<Map<String, Object>> menulist) {
		this.menulist = menulist;
	}
	
	@Override
	public String toString() {
		return "Member [userid=" + userid + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", age=" + age + ", clientIp=" + clientIp + ", salt=" + salt + ", menulist=" + menulist + "]";
	}
}
