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
package oasis.cmmn.service.impl;

import java.io.OutputStreamWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import lombok.extern.slf4j.Slf4j;
import oasis.cmmn.BigDataCSVResultHandler;
import oasis.cmmn.ExcelResultHandler;
import oasis.cmmn.WebHelper;
import oasis.cmmn.service.CommonService;
import oasis.core.helper.StringHelper;
import oasis.member.service.impl.MemberMapper;

/**
 * @Class Name : ProgramServiceImpl.java
 * @Description : program Business Implement Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2016. 10. 17           최초생성
 *
 * @author 
 * @since 2016. 10. 17
 * @version 1.0
 * @see
 *
 *  Copyright (C) by CJLION All right reserved.
 */

@Slf4j
@Service("commonService")
public class CommonServiceImpl extends EgovAbstractServiceImpl implements CommonService {

	// TODO mybatis 사용
	@Resource(name="memberMapper")
	private MemberMapper memberDAO;	

	@Resource(name="commonMapper")
	private CommonMapper commonDAO;	
	
	@Override
	public void exceldownload(Map<String, Object> parmas, ExcelResultHandler reusltHandler) throws Exception {
		// TODO Auto-generated method stub
		memberDAO.exceldownload(parmas, reusltHandler);
	}

	@Override
	public SXSSFWorkbook excelBigDataDownload(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		return commonDAO.execBigDataExcelProc(params);
	}

	@Override
	public void createDataCsv(HttpServletRequest request, HttpServletResponse response, Map<String, Object> param) throws Exception {
		
		String[] colIds = (String[]) param.get("colIds");
		String[] headTitles = (String[]) param.get("headTitles");		
		String filename = (String) param.get("filename");
		param.put("mId", StringHelper.noNull(param.get("qNs")) + "." + StringHelper.noNull(param.get("qId")));		
		
	    response.setContentType("text/csv;charset=euc-kr");	
	    WebHelper.setDisposition(filename, request, response);
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		CSVWriter csv = null;		
		OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream(), "euc-kr");
		try {			
			csv = new CSVWriter(out, CSVWriter.DEFAULT_SEPARATOR,
                    				 CSVWriter.NO_QUOTE_CHARACTER,
                    				 CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    				 CSVWriter.DEFAULT_LINE_END);	
			
			BigDataCSVResultHandler handler = new BigDataCSVResultHandler(csv, headTitles, colIds);			
			commonDAO.listToOutUsingResultHandler(commonDAO.mapperId(param), param, handler);
		} catch (Exception e) {
			log.error("에러 발생: IOException");
		} finally {
			if(csv != null) {
				csv.flush();
				csv.close();
			}
			if(out != null) {
				out.close();
			}
		}
	}	
}
