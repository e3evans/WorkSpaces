package com.manpower.hbn.beans;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import org.hibernate.Hibernate;

public class SearchCandidate {
	
	private long id;
	private long resumeid;
	private long mycount;
	private String firstname;
	private String lastname;
	private String middlename;
	private String status;
	private String nationality;
	private String nationalnumber;
	private String nativelanguage;
	private String email;
	private String correspondemail;
	private String resumename;
	private String mimetype;
	
	
	public long getMycount() {
		return mycount;
	}

	public void setMycount(long mycount) {
		this.mycount = mycount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getResumeid() {
		return resumeid;
	}

	public void setResumeid(long resumeid) {
		this.resumeid = resumeid;
	}

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

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getNationalnumber() {
		return nationalnumber;
	}

	public void setNationalnumber(String nationalnumber) {
		this.nationalnumber = nationalnumber;
	}

	public String getNativelanguage() {
		return nativelanguage;
	}

	public void setNativelanguage(String nativelanguage) {
		this.nativelanguage = nativelanguage;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCorrespondemail() {
		return correspondemail;
	}

	public void setCorrespondemail(String correspondemail) {
		this.correspondemail = correspondemail;
	}

	public String getResumename() {
		return resumename;
	}

	public void setResumename(String resumename) {
		this.resumename = resumename;
	}

	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	private Blob resume;
	private  byte[] fileAsByteArray; 
	
	
	
	
	
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
	
	
}
