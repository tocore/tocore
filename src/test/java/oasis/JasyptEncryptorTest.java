package oasis;

import java.security.NoSuchAlgorithmException;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;

import oasis.core.helper.SHA256Helper;

public class JasyptEncryptorTest {

	@Test
	public void encryptorTest() {
		
		StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
        jasypt.setPassword("issprc");      //암호화 키(password)
        jasypt.setAlgorithm("PBEWithMD5AndDES");
        
        String encryptedText = jasypt.encrypt("autoset");//암호화
        String plainText = jasypt.decrypt(encryptedText);//복호화
 
        System.out.println("Encryptor : " + encryptedText); //암호화된 값
        System.out.println("plainText : " + plainText); //암호화된 값
        
        //패스워드는 알고리즘으로 생성시, 계속 변함..
        //항상틀림.
        //assertEquals("ICU8Jga7nibhQN8u7yTnHw==", encryptedText);
	}
	
	@Test
	public void cncryptorTest2 () {
		try {
			System.out.println("cncryptorTest2 : " + SHA256Helper.encrypt("saltqwer1234!"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
