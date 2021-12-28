package oasis.core.helper;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * 압축 유틸리티.
 */
public class XmlHelper {
	
	/**
	 * 인스턴스 생성 금지.
	 */
	private XmlHelper() {
	}
	
	/**
	 * xml 파일을 읽고 타입 클래스로 객체로 가져온다.
	 * @param xmlstring xml string.
	 * @param valueType 타입 클래스.
	 * @return 타입 클래스 객체. . 없으면 null	
	 * @throws JAXBException 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T unmarshal(String xmlstring, final Class<T> valueType) throws JAXBException {    
        JAXBContext jaxbContext = JAXBContext.newInstance(valueType); 
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller(); 
        return (T) unmarshaller.unmarshal(new StringReader(xmlstring));
	}
	
	/**
	 * url 로 부터 xml 데이터를 읽고 타입 클래스로 객체로 가져온다.
	 * @param url xml url.
	 * @param valueType 타입 클래스.
	 * @return 타입 클래스 객체. . 없으면 null	
	 * @throws JAXBException 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T unmarshal(URL url, final Class<T> valueType) throws JAXBException {    
		JAXBContext jaxbContext = JAXBContext.newInstance(valueType); 
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller(); 
		return (T) unmarshaller.unmarshal(url);
	}
	
	/**
	 * JSON 파일을 생성한다.
	 * @param jsonFile JSON 파일 객체.
	 * @param object JSON 내보내는 객체.
	 * @throws IOException JSON 생성 과정에서 발생하는 예외.
	 * @throws JAXBException 
	 */
	public static void marshal(OutputStream os, final Object object) throws IOException, JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
		Marshaller marshaller = jaxbContext.createMarshaller();		
		marshaller.marshal(object, os);
	}
}
