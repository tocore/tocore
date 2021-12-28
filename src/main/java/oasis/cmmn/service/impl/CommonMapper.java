package oasis.cmmn.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.ResultHandler;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import oasis.cmmn.ExcelResultHandler;
import oasis.cmmn.ReflectionHelper;

/**
 * 공용 Mapper 클래스
 * 
 * @author 
 *
 */
@Repository("commonMapper")
public class CommonMapper extends EgovAbstractMapper {
    
    @Resource(name = "reflectionHelper")
    ReflectionHelper reflectionHelper;
    
	/**
	 * 대용량 엑셀 변환 - out of memory 발생 방지
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public SXSSFWorkbook execBigDataExcelProc(Map<String, Object> params) throws Exception {
		
		String sheetName = (String)params.get("SheetName");
		
		Sheet sheet = null;
		ExcelResultHandler excelResultHandler = null;			
		SXSSFWorkbook workbook = new SXSSFWorkbook(100);
		
		CellStyle defaultStyle = workbook.createCellStyle();		
		defaultStyle.setBorderRight(BorderStyle.THIN);		
		defaultStyle.setBorderLeft(BorderStyle.THIN);
		defaultStyle.setBorderTop(BorderStyle.THIN);
		defaultStyle.setBorderBottom(BorderStyle.THIN);
		defaultStyle.setAlignment(HorizontalAlignment.CENTER);
		// defaultStyle.setVerticalAlignment(VerticalAlignment.MIDDLE);
		
		if (!"".equals(sheetName)) {
			sheet = workbook.createSheet(sheetName);
		} else {
			sheet = workbook.createSheet("sheet1");
		}
		excelResultHandler = new ExcelResultHandler(sheet, params);
		
		// handle mapping
		getSqlSession().select(mapperId(params), params, excelResultHandler);
		return workbook;
	}
	
	@SuppressWarnings("rawtypes")
	public void listToOutUsingResultHandler(String queryId, Map<String, Object> param, ResultHandler handler) {
		getSqlSession().select(mapperId(param), param, handler);
	}
	
	/**
	 * mapperId를 가져온다.
	 * 
	 * @param param
	 * @return
	 */
	public String mapperId(Map<String, Object> params) {
		String[] mapperArr = ((String)params.get("mapperId")).split("\\.");
		String packageName = "";		
		packageName = reflectionHelper.getPackageNameFromProxiedInterfaces(mapperArr[0]);
		
		String mapperId = packageName + "." + mapperArr[1];
		return mapperId;
	}	
}
