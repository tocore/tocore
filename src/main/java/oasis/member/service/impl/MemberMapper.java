/*
 * Copyright 2011 MOPAS(Ministry of Public Administration and Security).
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

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import oasis.cmmn.ExcelResultHandler;
import oasis.core.authentication.dto.Member;

/**
 * Member에 관한 데이터처리 매퍼 클래스
 *
 * @author  
 * @since 2016.10.17
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2016.10.17                  최초 생성
 *
 * </pre>
 */
@Mapper("memberMapper")
public interface MemberMapper {	
	
	List<Member> selectMemberList(Map<String, Object> parmas) throws Exception;
	
	void exceldownload(Map<String, Object> parmas, ExcelResultHandler reusltHandler) throws Exception;
	
	Member selectMember(Map<String, Object> parmas) throws Exception;
	
	boolean loginFailCountIncrease(Map<String, Object> parmas) throws Exception;
	
	boolean loginFailCountInitialize(Map<String, Object> parmas) throws Exception;
	
	List<Map<String, Object>> selectMenuList(Map<String, Object> parmas) throws Exception;
}
