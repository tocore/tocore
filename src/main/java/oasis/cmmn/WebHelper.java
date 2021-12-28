package oasis.cmmn;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebHelper {
	
	public static void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String browser = WebHelper.getBrowser(request);

		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;
		filename = filename==null?"":filename;
		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}
	
	public static String getBrowser(HttpServletRequest request) {
    	String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
			return "Trident";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		}
		return "Firefox";
    }
	
	public static String getBrowserinfo(HttpServletRequest request) {
		String userBrs = request.getHeader("User-Agent");
		if (userBrs.indexOf("Swing") != -1) {
			userBrs = "Swing";
		} else if (userBrs.indexOf("MSIE") != -1 || userBrs.indexOf("Trident") != -1) {
			if (userBrs.indexOf("Mozilla/5.0") != -1) { // HTML5 를 지원하는 브라우저
				if (userBrs.indexOf("rv:11.0") != -1) {
					userBrs = "Internet Explorer 11";
				} else if (userBrs.indexOf("MSIE 10.0") != -1) {
					userBrs = "Internet Explorer 10";
				} else if (userBrs.indexOf("MSIE 9.0") != -1) {
					userBrs = "Internet Explorer 9";
				}
			} else {
				if (userBrs.indexOf("MSIE 8.0") != -1) {
					userBrs = "Internet Explorer 8";
				} else if (userBrs.indexOf("MSIE 7.0") != -1) {
					if (userBrs.indexOf("Trident/7.0") != -1 || (userBrs.indexOf("NT 10.0;") != -1 && userBrs.indexOf("Trident/8.0") != -1)) {
						userBrs = "Internet Explorer 11 (호환성보기)";
					} else if (userBrs.indexOf("Trident/6.0") != -1) {
						userBrs = "Internet Explorer 10 (호환성보기)";
					} else if (userBrs.indexOf("Trident/5.0") != -1) {
						userBrs = "Internet Explorer 9 (호환성보기)";
					} else {
						userBrs = "Internet Explorer 7";
					}
				} else if (userBrs.indexOf("MSIE 6.0") != -1) {
					userBrs = "Internet Explorer 6";
				} else if (userBrs.indexOf("MSIE 5.5") != -1) {
					userBrs = "Internet Explorer 5.5";
				} else if (userBrs.indexOf("MSIE 5.2") != -1) {
					userBrs = "Internet Explorer 5.2";
				} else if (userBrs.indexOf("MSIE 5.1") != -1) {
					userBrs = "Internet Explorer 5.1";
				} else if (userBrs.indexOf("MSIE 5.0") != -1) {
					userBrs = "Internet Explorer 5";
				}
			}
		} else if (userBrs.indexOf("Edge") != -1) {
			userBrs = "Edge";
		} else if (userBrs.indexOf("Chrome") != -1) {
			userBrs = "Chrome";
		} else if (userBrs.indexOf("Firefox") != -1) {
			userBrs = "Firefox";
		} else if (userBrs.indexOf("Safari") != -1) {
			userBrs = "Safari";
		} else if (userBrs.indexOf("Opera") != -1) {
			userBrs = "Opera";
		}
		return userBrs;
	}
}
