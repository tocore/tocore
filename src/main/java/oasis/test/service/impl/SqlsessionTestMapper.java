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
package oasis.test.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Repository("sqlsessionTestMapper")
public class SqlsessionTestMapper extends EgovAbstractMapper {	
	
	public void createData(List<Map<String, Object>> paramList) throws Exception {		
		SqlSession sqlSession = getSqlSession();
        try {
        	
        	Map<String, Object> param = new HashMap<String, Object>();
        	param.put("USER_ID", "test");
        	List<Member> list = sqlSession.selectList("oasis.member.service.impl.MemberMapper.selectMember", param);        	
        	for (Member member : list) {
        		System.out.println(member.toString());
			}
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        }  
	}	
	
	public void createDataBatch(List<Map<String, Object>> paramList) throws Exception {
		SqlSession sqlSession = getSqlSession();
        try {
        	
        	Map<String, Object> param = new HashMap<String, Object>();
        	param.put("USER_ID", "test");
        	List<Member> list = sqlSession.selectList("oasis.member.service.impl.MemberMapper.selectMember", param);        	
        	for (Member member : list) {
        		System.out.println(member.toString());
			}
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        }    
	}
}
