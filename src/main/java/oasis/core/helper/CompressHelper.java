package oasis.core.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * 압축 유틸리티.
 */
public class CompressHelper {
	
	/** encoding */
	private final static String UTF_8 = "UTF-8";
	
	/** 버퍼 사이즈 */
	private final static int BUFFER_SIZE = 1024 * 4;
	
	/**
	 * 인스턴스 생성 금지.
	 */
	private CompressHelper() {
	}

	/**
	 * ZipArchiveOutputStream 객체를 생성해서 가져온다.
	 * @param zipFile 압축 파일 객체.
	 * @return ZipArchiveOutputStream 객체
	 * @throws FileNotFoundException 객체를 생성해서 가져오는 과정에서 발생하는 예외.
	 */
	public static ZipArchiveOutputStream getZipArchiveOutputStream(final File zipFile) throws FileNotFoundException {
		ZipArchiveOutputStream zaos = new ZipArchiveOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
		
		zaos.setEncoding(UTF_8);
		zaos.setFallbackToUTF8(true);
		zaos.setUseLanguageEncodingFlag(true);
		zaos.setCreateUnicodeExtraFields(ZipArchiveOutputStream.UnicodeExtraFieldPolicy.NOT_ENCODEABLE);
		
		return zaos;
	}
	
	/**
	 * ZIP 파일 압축한다. <br>
	 * ZIP 파일 압축 과정에서 실패하면 파일은 삭제한다.
	 * @param fileList File 객체를 가진 List 파일 목록.
	 * @param zipFile 압축 파일 명.
	 * @param isDirectory 디렉토리 포함해서 압축파일 생성 여부(true : 생성, false : 생성 없음).
	 * @throws IOException 압축 진행과정에서 발생하는 예외.
	 */
	public static void compressZip(final List<File> fileList, final File zipFile, final boolean isDirectory) throws IOException {
		if (zipFile == null) {
			throw new IOException("압축 파일명이 존재하지 않습니다.");
		} else if (fileList == null || fileList.isEmpty()) {
			throw new IOException("압축 파일 목록이 존재하지 않습니다.");
		}
		
		ZipArchiveOutputStream zaos = null;
		try {
			zaos = getZipArchiveOutputStream(zipFile);
			
			for (int i = 0; i < fileList.size(); i++) {
				compressZip(zaos, fileList.get(i), isDirectory);
			}
		} catch (IOException e) {			
			zipFile.delete();
			throw e;
		} finally {
			if (zaos != null) {
				zaos.close();
			}
		}
	}
	
	/**
	 * zip 파일을 압축한다.
	 * @param zaos ZipArchiveOutputStream 객체.
	 * @param file 압축 파일 및 압축 디렉토리 객체.
	 * @param isDirectory 디렉토리 포함해서 압축파일 생성 여부(true : 생성, false : 생성 없음).
	 * @throws IOException 압축 진행과정에서 발생하는 예외.
	 */
	private static void compressZip(final ZipArchiveOutputStream zaos, final File file, final boolean isDirectory) throws IOException {
		ArchiveEntry archiveEntry = null;
		
		if (file.isFile()) {
			if (isDirectory) {
				compressAppendZip(zaos, file, file.getPath());
			} else {
				compressAppendZip(zaos, file, file.getName());
			}
		} else if (file.isDirectory()) {
			String directory = file.getPath() + File.separator;
			if (isDirectory) {
				archiveEntry =  new ZipArchiveEntry(directory);
				zaos.putArchiveEntry(archiveEntry);
			}
			
			String[] list = file.list();
			if (list != null && list.length != 0) {
				for (int i = 0; i < list.length; i++) {
					compressZip(zaos, FileUtils.getFile(directory + list[i]), isDirectory);
				}
			}
		}
	}
	
	/**
	 * ZIP 압축 파일에 압축 대상 파일을 추가한다.
	 * @param zaos ZipArchiveOutputStream 객체.
	 * @param file 압축 대상 File 객체.
	 * @param name 압축 대상 파일명.
	 * @throws IOException 압축 진행과정에서 발생하는 예외.
	 */
	public static void compressAppendZip(final ZipArchiveOutputStream zaos, final File file, final String name) throws IOException {
		BufferedInputStream bis = null;
		
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
			zaos.putArchiveEntry(new ZipArchiveEntry(name));
			
			int n = 0;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((n = bis.read(buffer)) != -1) {
				zaos.write(buffer, 0, n);
			}
		} finally {
			zaos.flush();
			zaos.closeArchiveEntry();	
			if (bis != null) {
				bis.close();
			}
		}
	}
	
	/**
	 * zip 압축 파일을 푼다.
	 * @param zipFile 압축 File 객체.
	 * @param dirFile 압축 해제할 디렉토리 파일.
	 * @throws IOException 압축해제 하는 과정에서 발생하는 예외.
	 */
	public static void decompressZip(final File zipFile, final File dirFile) throws IOException {
		ZipArchiveInputStream zais = null;
		try {
			zais = new ZipArchiveInputStream(new BufferedInputStream(new FileInputStream(zipFile)));
			decompressZip(zais, dirFile);
		} finally {
			if (zais != null) {
				zais.close();
			}
		}
	}
	
	/**
	 * zip 압축 파일을 푼다.
	 * @param archiveInputStream ArchiveInputStream 객체.
	 * @param dirFile 압축 해제할 디렉토리 파일.
	 * @throws IOException 압축해제 하는 과정에서 발생하는 예외.
	 */
	public static void decompressZip(final ArchiveInputStream archiveInputStream, final File dirFile) throws IOException {
		OutputStream os = null;
		
		try {
			// 압축 해제.
			ArchiveEntry archiveEntry = null;
			while ((archiveEntry = archiveInputStream.getNextEntry()) != null) {
				// 디렉토리 여부.
				if (archiveEntry.isDirectory()) {
					continue;
				}
				
				// 압축 해제 파일
				File decompressFile = FileUtils.getFile(dirFile, archiveEntry.getName());
				
				// 디렉토리 생성.
				File parent = decompressFile.getParentFile();
				if (!parent.exists()) {
					parent.mkdirs();
				}
				
				// 압축 해제 파일 생성.
				os = new FileOutputStream(decompressFile);
				IOUtils.copy(archiveInputStream, os);
			}
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}
}
