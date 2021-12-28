package egovframework.base;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import egovframework.rte.fdl.property.EgovPropertyService;
import lombok.extern.slf4j.Slf4j;
import oasis.core.annotation.DescriptionParametes;

@Slf4j
@Controller
public class BaseController implements IConstants {
		
	/** EgovPropertyService */
	@Resource(name="propertiesService")
	protected EgovPropertyService propertiesService;	
		
	protected Map<String, String[]> params;
	
	/**
	 * 초기값 세팅
	 * @param request
	 * @param response
	 * @param commandMap
	 * @throws ModelAndViewDefiningException 
	 * @throws Exception
	 */
	@ModelAttribute("init")	
	public void init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.params = request.getParameterMap();
		printInfo(request, this.params);
	}
	
	private void outputParams(Map<String, String[]> map) {		
		synchronized (map) {
			for (String key : map.keySet()) {
				String[] value = map.get(key);
				for (int i = 0; i < value.length; i++) {
					log.debug( String.format("%s = %s", key, value[i]));
				}
			}
		}
	}
	
	/**
	 * @param request
	 * @param paramMap
	 * @throws Exception
	 */
	private void printInfo (HttpServletRequest request, Map<String, String[]> paramMap) throws Exception {	
				
		String mode = propertiesService.getString("system.env");
		// DescriptionParametes 어노테이션 구현
		DescriptionParametes[] desp = (DescriptionParametes[]) this.getClass().getAnnotationsByType(DescriptionParametes.class);
		if (desp.length > 0) {					
			if (DEV.equals(mode)) {
				if (log.isDebugEnabled()) {
					log.debug("===========================================================");
					log.debug("system.env : " + DEV);
	//				log.debug("Remote Address : " + request.getRemoteAddr());
					log.debug("Request URL : " + request.getRequestURL());			
					outputParams(paramMap);	
					log.debug("===========================================================");
				}
			}
		}
	}	
}
