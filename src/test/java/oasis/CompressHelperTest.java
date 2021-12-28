package oasis;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import oasis.core.helper.CompressHelper;

public class CompressHelperTest {

	@Test
	public void compresstest() {
		
//		String path = "C:\\Logs\\artris\\log.zip";
		String path2 = "C:\\Logs\\artris\\log.zip";
//		File zipFile = new File(path);
		File zipFile2 = new File(path2);
		
		List<File> fileList = new ArrayList<File>();
		fileList.add(new File("C:\\Logs\\artris\\log-2020-07-16.log"));
		fileList.add(new File("C:\\Logs\\artris\\log-2020-07-17.log"));
		fileList.add(new File("C:\\Logs\\artris\\log-2020-07-20.log"));
		
		try {
			// CompressHelper.compressZip(fileList, zipFile, true);
			CompressHelper.compressZip(fileList, zipFile2, false);
			assertTrue(true);
		} catch (IOException e) {
			assertFalse(true);
			e.printStackTrace();
		}
	}
	
	@Test
	public void deCompresstest() {
		
		String file = "C:\\Logs\\artris\\log.zip";
		String dirFile = "C:\\Logs\\artris\\1\\";
		
		try {
			CompressHelper.decompressZip(new File(file), new File(dirFile));
			assertTrue(true);
		} catch (IOException e) {
			assertFalse(true);
			e.printStackTrace();
		}
	}

}
