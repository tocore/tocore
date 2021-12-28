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
package oasis.test.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import oasis.test.service.SqlsessionTestService;

/**
 * @Class Name : ProgramServiceImpl.java
 * @Description : program Business Implement Class
 * @Modification Information
 * @
 * @  수정일      	  수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2016.10.17            	  최초생성
 *
 * @author 강문성
 * @since 2016. 10. 17
 * @version 1.0
 * @see
 *
 *  Copyright (C) by CJLION All right reserved.
 */

@Service("sqlsessionTestService")
public class SqlsessionTestServiceImpl implements SqlsessionTestService {
	
	// 공통
	@Resource(name="sqlsessionTestMapper")
	private SqlsessionTestMapper sqlsessionTestMapper;	
	
	@Override
	public boolean createData(List<Map<String, Object>> paramList) throws Exception {		
		sqlsessionTestMapper.createData(paramList);
		return true;
	}

	@Override
	public boolean createDataBatch(List<Map<String, Object>> paramList) throws Exception {
		sqlsessionTestMapper.createDataBatch(paramList);
		return true;
	}
}
