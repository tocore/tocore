package oasis.cmmn;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelResultHandler implements ResultHandler<T> {
	
	private Sheet sheet;
	
	private int strartRow = 0;// 시작 행
	
	private int strartCell = 0;// 시작 컬럼
	
	public ExcelResultHandler (Sheet sheet, Map<String, Object> params) {	
		try {
			this.sheet = sheet;
			this.strartRow = (int)params.get("StartRow");
			this.strartCell = (int)params.get("StartCell");
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void handleResult(ResultContext context) {
		// TODO Auto-generated method stub
		if (context.getResultObject() instanceof Map) {	
			
			LinkedHashMap<String, Object> linkedMap = (LinkedHashMap<String, Object>) context.getResultObject();			
			
			Row row = null;
			row = sheet.createRow((context.getResultCount()-1) + strartRow);
			
			int cellIdx = strartCell;
			Iterator<String> keys = linkedMap.keySet().iterator();			
			while (keys.hasNext()) {
				String key = keys.next() + "";
				String value = linkedMap.get(key) + "";
				
				Cell cell = row.createCell(cellIdx);
				cell.setCellValue(value);				
				cellIdx ++;
			}			
		}
	}	
}
