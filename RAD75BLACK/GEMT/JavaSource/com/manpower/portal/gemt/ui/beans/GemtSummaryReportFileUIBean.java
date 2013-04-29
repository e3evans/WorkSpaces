package com.manpower.portal.gemt.ui.beans;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class GemtSummaryReportFileUIBean extends BaseUIBean {

	private String gemt_sum_file_name;
	private  byte[] fileAsByteArray; 
	private InputStream fileAsStream;
	
	public InputStream getFileAsStream() {
		return fileAsStream;
	}
	public void setFileAsStream(InputStream file) {
		this.fileAsStream = file;
		try{
			 BufferedInputStream bis = new BufferedInputStream(file);
		       ByteArrayOutputStream bao = new ByteArrayOutputStream(); 
		       byte[] buffer = new byte[40000]; 
		       int length = 0; 
		       while ((length = bis.read(buffer)) != -1) { 
		       bao.write(buffer, 0, length);

		       } 
		       bao.close(); 
		       bis.close(); 
		       byte[] buf = bao.toByteArray(); 
		       setFileAsByteArray(buf);
		       
		}catch(Exception e)
		{
			e.printStackTrace();
		};		
	}
	public byte[] getFileAsByteArray() {
		return fileAsByteArray;
	}
	public void setFileAsByteArray(byte[] fileAsByteArray) {
		this.fileAsByteArray = fileAsByteArray;
	}
	public String getGemt_sum_file_name() {
		return gemt_sum_file_name;
	}
	public void setGemt_sum_file_name(String gemt_sum_file_name) {
		this.gemt_sum_file_name = gemt_sum_file_name;
	}
}
