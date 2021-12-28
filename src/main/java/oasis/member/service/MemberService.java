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
package oasis.member.service;

import java.util.List;
import java.util.Map;

import oasis.core.authentication.dto.Member;


/**
 * <pre>
 * **************************************************
 * MemberService class
 * 사용자 관련 클래스
 * **************************************************
 * </pre>
 * @File Name : MemberService.java 
 * @author : 강문성
 * @date 
 *  
 */
public interface MemberService {
	
	/**
	 * member
	 * @param list - member
	 * @param user - 사용자
	 * @return 사용자 총 갯수
	 * @exception
	 */
	List<Member> selectMemberList(Map<String, Object> parmas) throws Exception;		
	boolean loginFailCountIncrease(Map<String, Object> params) throws Exception;		
	boolean loginFailCountInitialize(Map<String, Object> params) throws Exception;	
}
