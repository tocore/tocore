package oasis.cmmn.view;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

@SuppressWarnings("deprecation")
public class CommonExcelView extends AbstractExcelView {
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(	Map<String, Object> model,
										HSSFWorkbook workbook, 
										HttpServletRequest req, 
										HttpServletResponse res ) throws Exception {
		
		String fileName 	= (String)model.get("filename");
		List<String> heads 	= (List<String>)model.get("heads");		
		List<Map<String, String>> data = (List<Map<String, String>>)model.get("data");
		List<String> sort = (List<String>)model.get("sort");
		
		String userAgent = req.getHeader("User-Agent");
		if(userAgent.indexOf("MSIE") > -1){
			fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20"); 
		}else{
			fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1"); 
		}
		        
		res.setHeader("Content-Disposition","attachment; filename=\"" + fileName +"\""); 
		res.setContentType("Application/Msexcel");
		res.setHeader("Content-Transfer-Encoding", "binary;"); 	
		
		HSSFSheet sheet = createFirstSheet(workbook);
		createColumnHead(sheet, heads);			
		createPageRow(sheet, data, 1, sort);
	}
	
	private HSSFSheet createFirstSheet(HSSFWorkbook workbook) {
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "sheet1");
		sheet.setColumnWidth(0, 256*30);	
		return sheet;
	}
	
	private void createColumnHead(HSSFSheet sheet, List<String> heads) {			
		HSSFRow firstRow = sheet.createRow(0);	
		
		int columnIndex = 0;
		for(String head : heads)
		{
			HSSFCell cell = firstRow.createCell(columnIndex);		
			cell.setCellValue(head);
			columnIndex ++;
		}
	}
	
	private void createPageRow(HSSFSheet sheet, List<Map<String, String>> list, int startRow, List<String> sort) {
			
		int rowidx = startRow;
		for (Map<String, String> map : list)
		{
			HSSFRow row = sheet.createRow(rowidx);	
			
			int colidx = 0;
			for (int i = 0; i < sort.size(); i++) {
				
				String key = sort.get(i);				
				String val = String.valueOf(map.get(key)).replaceAll("null", "");
				
				HSSFCell cell = row.createCell(colidx);
				cell.setCellValue(val);
				
				colidx++;
			}
			
			rowidx++;
		}
	}
}
