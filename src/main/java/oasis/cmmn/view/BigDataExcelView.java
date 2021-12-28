package oasis.cmmn.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.servlet.view.AbstractView;

import oasis.cmmn.WebHelper;


public class BigDataExcelView extends AbstractView {
	
	/** View 명 */
	public static final String NAME = "bigDataExcelView";

	/** 컨텐트 타입을 엑셀형식으로 설정한다. */
	public BigDataExcelView() {
		this.setContentType("application/x-msexcel");
	}
	
	/**
	 *
	 * 대용량 엑셀 파일 다운로드
	 */
	@Override
	protected void renderMergedOutputModel(@SuppressWarnings("rawtypes") Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String filename = (String)model.get("filename");
		
		response.setHeader("Set-Cookie", "fileDownload=true; path=/");
		response.setHeader("Content-Transfer-Encoding", "binary");
		WebHelper.setDisposition(filename, request, response);
						
		SXSSFWorkbook workbook = (SXSSFWorkbook) model.get("sw");
		workbook.write(response.getOutputStream());
		workbook.dispose();// 임시 파일 제거
	}	
}
