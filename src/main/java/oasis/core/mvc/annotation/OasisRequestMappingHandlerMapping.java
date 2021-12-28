package oasis.core.mvc.annotation;

import java.lang.reflect.Method;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import lombok.extern.slf4j.Slf4j;
import oasis.core.annotation.DescriptionParametes;

/**
 * 	@author admin
 *	현재 사용안함 - 필요시 사용을 위해 구현
 *	파라미터에 특정 값 추가 용도로 사용
 */
@Slf4j
@Component
public class OasisRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
	
	@Override
	protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
		DescriptionParametes classDesc = AnnotationUtils.getAnnotation(method, DescriptionParametes.class);
		log.debug("DescriptionParametes >>>>>>>>>>>>>>>> " + classDesc);
		super.registerHandlerMethod(handler, method, mapping);			
	}
}
