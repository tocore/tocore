package oasis.core.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Helper {

	public SHA256Helper() {
		// TODO Auto-generated constructor stub
	}
	
	public static String encrypt(String source) throws NoSuchAlgorithmException {
		
		MessageDigest sh = MessageDigest.getInstance("SHA-256");
		sh.update(source.getBytes()); 
		byte byteData[] = sh.digest();
	
		StringBuffer sb = new StringBuffer();		
		for(int i = 0 ; i < byteData.length ; i++) {
			sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}
}
