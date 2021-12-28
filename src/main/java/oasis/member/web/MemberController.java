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
package oasis.member.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import oasis.cmmn.service.CommonService;
import oasis.core.annotation.DescriptionParametes;
import oasis.core.helper.StringHelper;
import oasis.member.service.MemberService;

/**
 * <pre>
 * **************************************************
 * MemberController class
 * member control
 * **************************************************
 * </pre>
 * @File Name : MemberController.java 
 * @author : 
 * @date 
 * 
 */

// @RestController - @ResponseBody 역할까지 처리
@Slf4j
@Controller
@RequestMapping("/member")
@DescriptionParametes
public class MemberController extends BaseController {
		
	@Resource(name = "memberService")
	private MemberService memberService;
		
	@Resource(name = "commonService")
	private CommonService commonService;
	
	@RequestMapping(value = "/exportExcel", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mapperId", "memberMapper.exceldownload");	
		params.put("SheetName", "sheet name");
		// params.put("StartRow", 2);
		// params.put("StartCell", 2);		
		SXSSFWorkbook workbook = commonService.excelBigDataDownload(params);
		
		ModelAndView modelAndView = new ModelAndView("bigDataExcelView");
		modelAndView.addObject("filename", "한글 test 1234.xlsx");
		modelAndView.addObject("sw", workbook);
		return modelAndView;
	}
	
	@RequestMapping(value = "/exportCSV", method = {RequestMethod.POST, RequestMethod.GET})
	public void exportCSV(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		/* 샘플 
		String filename = "한글 &!@3455.csv";
		String headTitlesStr  = "번호,사용자 아이디,사용자 이름,직책명,부서명,userSttusNm,registDt";
		String colIdsStr  = "ROWNUM,USER_ID,USER_NM,JOB_NM,USER_GROUP_CODE_NM,USER_STTUS_NM,REGIST_DT";
		commandMap.put("qNs", "memberMapper");
		commandMap.put("qId", "getMemberExcelList");
		*/
		
		String filename  = StringHelper.noNull(params.get("tFileNm"));
		String colIdsStr  = StringHelper.noNull(params.get("colIds"));
		String headTitlesStr  = StringHelper.noNull(params.get("headTitles"));
		
		if ("".equals("filename") || "".equals("headTitlesStr")) {
			log.error("BigDataCSVDownController::createViewDataCsvBigData -> 파라미터가 부족합니다.");;
			return;
		}		
		String[] colIds = colIdsStr.split(",");	
		String[] headTitles = headTitlesStr.split(",");		
		
		Map<String, Object> map = new HashMap<String, Object>();		
		map.put("headTitles", headTitles);
		map.put("colIds", colIds);
		map.put("filename", filename);
		
		commonService.createDataCsv(req, resp, map);
	}
	
	@RequestMapping(value = "/join", method = {RequestMethod.POST})	
	public @ResponseBody Map<String , Object> joinMember(@RequestBody Map<String , Object> map) throws Exception {
		log.debug("join");
		return map;
	}	
}
