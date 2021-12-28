package oasis.cmmn;

import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


/**
 * <pre>
 * **************************************************
 * ReflectionHelper class
 * Reflection 관련 클래스
 * **************************************************
 * </pre>
 * @File Name : ReflectionHelper.java 
 * @author : 
 * @date 
 *  
 */
@Service("reflectionHelper")
public class ReflectionHelper {
	
    @Autowired
    private ApplicationContext applicationContext;    
    
	/**
	 * 인터페이스 빈 이름으로 팩캐지 명를 구해온다.
	 * @param beanName
	 * @return 팩캐지명
	 * @exception
	 */
	public synchronized String getPackageNameFromProxiedInterfaces(String beanName) {
		
		Object obj = applicationContext.getBean(beanName);    
		Class<?>[] arrcls = AopProxyUtils.proxiedUserInterfaces(obj);
		
		return arrcls[0].getName();
	}
	
	/**
	 * 클래스 빈 이름으로 패캐지 명를 구해온다.
	 * @param beanName
	 * @return 팩캐지명
	 * @exception
	 */
	public synchronized String getPackageNameFromTargetClass(String beanName) {
		
		Object obj = applicationContext.getBean(beanName);
		Class<?> cls = AopProxyUtils.ultimateTargetClass(obj);
		
		return cls.getPackage().getName();
	}
}
