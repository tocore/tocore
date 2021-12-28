package oasis;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import oasis.core.helper.XmlHelper;

public class XmlHelperTest {

	@Test
	public void testUnmarshal() {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		sb.append("<stockprice querytime=\"2019-06-03 14:18:12\" >");
		sb.append("<TBL_DailyStock>");
		sb.append("<DailyStock day_Date=\"19/06/03\" day_EndPrice=\"115,000\"/>");
		sb.append("</TBL_DailyStock>");
		sb.append("</stockprice>");
		
		try {			
			StockPrice stockPrice = XmlHelper.unmarshal(sb.toString(), StockPrice.class);			
			System.out.println(stockPrice);
			assertTrue(true);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMarshal() {
		OutputStream os = null;
		try {
			os = new FileOutputStream("employee.xml");			
			try {
				XmlHelper.marshal(os, new StockPrice());
			} catch (IOException | JAXBException e) {				
				e.printStackTrace();
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
