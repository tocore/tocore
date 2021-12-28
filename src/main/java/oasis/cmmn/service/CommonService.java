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
package oasis.cmmn.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import oasis.cmmn.ExcelResultHandler;


/**
 * <pre>
 * **************************************************
 * MemberService class
 * 사용자 관련 클래스
 * **************************************************
 * </pre>
 * @File Name : MemberService.java 
 * @author : 
 * @date 
 *  
 */
public interface CommonService {
	
	/**
	 * 엑셀 다운로드
	 * @param list - member
	 * @param user - 사용자
	 * @return 사용자 총 갯수
	 * @exception
	 */
	void exceldownload(Map<String, Object> parmas, ExcelResultHandler reusltHandler) throws Exception;	
	
	/**
	 * 엑셀 다운로드
	 * @param list - member
	 * @param user - 사용자
	 * @return 사용자 총 갯수
	 * @exception
	 */
	SXSSFWorkbook excelBigDataDownload(Map<String, Object> params) throws Exception;	
	
	/**
	 * 대용량 CSV 다운로드
	 * @param request
	 * @param response - 사용자
	 * @return param\
	 * @exception
	 */
	void createDataCsv(HttpServletRequest request, HttpServletResponse response, Map<String, Object> param) throws Exception;
}
