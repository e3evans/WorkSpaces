package com.manpower.portal.gemt.hbn.beans;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import org.hibernate.Hibernate;

public class GemtSummaryReportFileHbnBean extends BaseHibernateBean {
	
	private GemtSummaryReportHbnBean gemt_sum_report;
	private String gemt_sum_file_name;
	private  byte[] fileAsByteArray; 
	private Blob gemt_sum_file_object;

	/**
	 * Convert java.sql.Blob object to a byte array
	 * 
	 * @param fromBlob
	 * @return
	 */
	 private byte[] toByteArray(Blob fromBlob) {  
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		try {   
			return toByteArrayImpl(fromBlob, baos);  } 
			catch (SQLException e) 
			{   throw new RuntimeException(e);  
			}
		 	catch (IOException e) 
			{   throw new RuntimeException(e);  
			}
		 	finally 
			{   if (baos != null) 
				{    try 
					{     baos.close();    
					} 
					catch (IOException ex) {    }   
				}  
			} 
	} 
	 /**
	  * Convert java.sql.Blob object to a byte array
	  * 
	  * @param fromBlob
	  * @param baos
	  * @return byte array
	  * @throws SQLException
	  * @throws IOException
	  */
	 private byte[] toByteArrayImpl(Blob fromBlob, ByteArrayOutputStream baos)  throws SQLException, IOException 
	 {  byte[] buf = new byte[40000];
	   InputStream is = fromBlob.getBinaryStream();
	 	  try 
	 	     {   for (;;) 
	 		{    int dataSize = is.read(buf);
	 		    if (dataSize == -1)     break;
	     			baos.write(buf, 0, dataSize);
	 	        }  
	 	      } 
	 	finally 
	 	{   if (is != null) 
	 		{    try 
	 			{     is.close();    } 
	 			catch (IOException ex) {    }
	 	       }
	         }  return baos.toByteArray();
	 	
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
	
	public GemtSummaryReportHbnBean getGemt_sum_report() {
		return gemt_sum_report;
	}
	public void setGemt_sum_report(GemtSummaryReportHbnBean gemt_sum_report) {
		this.gemt_sum_report = gemt_sum_report;
	}
	
	public Blob getGemt_sum_file_object() {
		if(this.fileAsByteArray != null){
			
			gemt_sum_file_object=Hibernate.createBlob(this.fileAsByteArray);
			return gemt_sum_file_object;
			
		}else{
		
			return null;
			
		}
	}
	public void setGemt_sum_file_object(Blob gemt_sum_file_object) {
		this.gemt_sum_file_object = gemt_sum_file_object;
		if(gemt_sum_file_object!=null)
		{
			this.fileAsByteArray=toByteArray(gemt_sum_file_object);
		}
	}		 		
}
