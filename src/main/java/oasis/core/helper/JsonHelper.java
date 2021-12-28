package oasis.core.helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 압축 유틸리티.
 */
public class JsonHelper {
	
	/**
	 * 인스턴스 생성 금지.
	 */
	private JsonHelper() {
	}
	
	/**
	 * JSON 파일을 읽고 타입 클래스로 객체로 가져온다.
	 * @param jsonFile JSON 파일 객체.
	 * @param valueType 타입 클래스.
	 * @return 타입 클래스 객체. . 없으면 null
	 * @throws IOException JSON 파일을 읽는 과정에서 발생하는 예외.
	 */
	public static <T> T jsonReadValue(final File jsonFile, final Class<T> valueType) throws IOException {
		// ObjectMapper 객체 생성 및 설정.
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);         	// 알수 없는 프로퍼티 FAIL 처리 하지 않기
		objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);        	// 싱글 값을 배열 처리.
		objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT); 	// "" 따옴표를 NULL 처리
		
		return objectMapper.readValue(jsonFile, valueType);
	}
	
	/**
	 * JSON 파일을 읽고 타입 클래스로 객체로 가져온다.
	 * @param jsonFile JSON 파일 객체.
	 * @param valueType 타입 클래스.
	 * @return 타입 클래스 객체. . 없으면 null
	 * @throws IOException JSON 파일을 읽는 과정에서 발생하는 예외.
	 */
	public static <T> T jsonReadValue(final String jsonString, final Class<T> valueType) throws IOException {
		// ObjectMapper 객체 생성 및 설정.
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);         	// 알수 없는 프로퍼티 FAIL 처리 하지 않기
		objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);        	// 싱글 값을 배열 처리.
		objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT); 	// "" 따옴표를 NULL 처리
		
		return objectMapper.readValue(jsonString, valueType);
	}
	
	/**
	 * JSON 파일을 생성한다.
	 * @param jsonFile JSON 파일 객체.
	 * @param object JSON 내보내는 객체.
	 * @throws IOException JSON 생성 과정에서 발생하는 예외.
	 */
	public static void jsonWriteValue(final File jsonFile, final Object object) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S"));
		objectMapper.writeValue(jsonFile, object);
	}
}
