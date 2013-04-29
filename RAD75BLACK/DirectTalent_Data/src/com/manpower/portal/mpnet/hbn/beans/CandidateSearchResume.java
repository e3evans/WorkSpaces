package com.manpower.portal.mpnet.hbn.beans;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Hibernate;

public class CandidateSearchResume implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5841376391602760972L;
	/**
	 * 
	 */

	
	private long id;
	private String resumeName;
	private String mimeType;
	private Date lastResumeDate;
	private Blob resume;
	private  byte[] resumeArray ;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getResumeName() {
		return resumeName;
	}
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public Date getLastResumeDate() {
		return lastResumeDate;
	}
	public void setLastResumeDate(Date lastResumeDate) {
		this.lastResumeDate = lastResumeDate;
	}
	public Blob getResume() {
		
		if(this.resumeArray != null){
			return Hibernate.createBlob(this.resumeArray);
		}else{
			return null;
		}
		
	}
	/**
	 * @param resume The resume to set.
	 */
	public void setResume(Blob resume) {
		this.resume = resume;
		if(resume == null){}
		else
		{
		this.resumeArray = this.toByteArray(resume);
		}
	}
	public byte[] getResumeArray() {
		return resumeArray;
	}
	/**
	 * @param resumeArray The resumeArray to set.
	 */
	public void setResumeArray(byte[] resumeArray) {
		this.resumeArray = resumeArray;
	}
	
	private byte[] toByteArray(Blob fromBlob) {  
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		try {   
			return toByteArrayImpl(fromBlob, baos);  } 
			catch (SQLException e) 
			{  
				e.printStackTrace();
				throw new RuntimeException(e);  
			}
		 	catch (IOException e) 
			{   e.printStackTrace();
		 		throw new RuntimeException(e);  
			}
		 	finally 
			{   if (baos != null) 
				{    try 
					{     baos.close();    
					}
					catch (IOException ex) {ex.printStackTrace();}   
				}  
			} 
	}
	
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
	 			catch (IOException ex) { ex.printStackTrace();   }
	 	       }
	         }  return baos.toByteArray();
	 	
	  }
}
