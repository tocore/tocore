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
package oasis.member.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import oasis.cmmn.service.impl.CommonMapper;
import oasis.core.authentication.dto.Member;
import oasis.member.service.MemberService;

/**
 * @Class Name : ProgramServiceImpl.java
 * @Description : program Business Implement Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2016. 10. 17           최초생성
 *
 * @author 강문성
 * @since 2016. 10. 17
 * @version 1.0
 * @see
 *
 *  Copyright (C) by CJLION All right reserved.
 */

@Service("memberService")
public class MemberServiceImpl extends EgovAbstractServiceImpl implements MemberService {

	// 회원정보
	@Resource(name="memberMapper")
	private MemberMapper memberDAO;
	
	// 공통
	@Resource(name="commonMapper")
	private CommonMapper commonDAO;	

	@Override
	public boolean loginFailCountIncrease(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.loginFailCountIncrease(params);
	}
	
	@Override
	public boolean loginFailCountInitialize(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.loginFailCountInitialize(params);
	}

	@Override
	public List<Member> selectMemberList(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.selectMemberList(params);
	}
}
