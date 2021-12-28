package oasis.cmmn;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import com.opencsv.CSVWriter;

import oasis.core.helper.StringHelper;

@SuppressWarnings("rawtypes")
public class BigDataCSVResultHandler implements ResultHandler {
	
	private CSVWriter csv;
	private String[] headTitles;
	private String[] colIds;
	
	/**
	 * @param csv
	 * @param headTitles
	 * @param colIds
	 */
	public BigDataCSVResultHandler(CSVWriter csv, String[] headTitles, String[] colIds) {	
		this.csv = csv;
		this.headTitles = headTitles;
		this.colIds = colIds;		
		CreateHead();
	}
	
	/* (non-Javadoc)
	 * @see org.apache.ibatis.session.ResultHandler#handleResult(org.apache.ibatis.session.ResultContext)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void handleResult(ResultContext context) {
		if (context.getResultObject() instanceof Map) {
			LinkedHashMap<String, Object> csvMap = (LinkedHashMap<String, Object>) context.getResultObject();			
			if (!csvMap.isEmpty()) {
				createBody(csvMap);
			}
		}
	}
	
	/**
	 * csv 헤더 생성
	 */
	private void CreateHead() {		
		if (headTitles.length>0) {
			csv.writeNext(headTitles);
		}
	}
	
	/**
	 * csv body 생성
	 * @param csvMap
	 */
	private void createBody(LinkedHashMap<String, Object> csvMap) {	
		String[] rowData = new String[colIds.length];		
		for (int i = 0; i < colIds.length; i++) {
			rowData[i] = StringHelper.noNull(csvMap.get(colIds[i]));			
		}
		csv.writeNext(rowData);		
	}
}
