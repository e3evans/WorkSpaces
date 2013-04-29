package com.manpower.hbn.beans;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import org.hibernate.Hibernate;

public class Resume {
	
	private long resume_id;
	private Blob resume;
	private  byte[] fileAsByteArray; 
	private String firstname;
	private String lastname;
	private String mime_type;
	private String resume_name;
	private String cand_type;
	
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMime_type() {
		return mime_type;
	}

	public void setMime_type(String mime_type) {
		this.mime_type = mime_type;
	}

	public String getResume_name() {
		return resume_name;
	}

	public void setResume_name(String resume_name) {
		this.resume_name = resume_name;
	}

	public byte[] getFileAsByteArray() {
		return fileAsByteArray;
	}

	public void setFileAsByteArray(byte[] fileAsByteArray) {
		this.fileAsByteArray = fileAsByteArray;
	}

	public Blob getResume() {
		if(this.fileAsByteArray != null){
			resume=Hibernate.createBlob(this.fileAsByteArray);
			return resume;
			
		}else{
			return null;
			
		}
	}

	public void setResume(Blob resume) {
		this.resume = resume;
		if(resume!=null)
		{
			this.fileAsByteArray=toByteArray(resume);
		}
		this.resume = resume;
	}

	public long getResume_id() {
		return resume_id;
	}

	public void setResume_id(long resume_id) {
		this.resume_id = resume_id;
	}

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

	public String getCand_type() {
		return cand_type;
	}

	public void setCand_type(String cand_type) {
		this.cand_type = cand_type;
	}


}
