package com.manpower.utility;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.filefilter.WildcardFileFilter;

import sun.misc.SoftCache;



/**
 * Servlet implementation class FileDistribution
 */
public class FileDistribution extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDistribution() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().println("TESTING THIS!!(GET)");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		ServletInputStream inputFromClient=null;
		FileOutputStream fos = null;
		try{
			Boolean doDelete = new Boolean((String)request.getHeader("deleteFiles"));
			
			if (doDelete){
				
				sb.append("BEGINNING FILES DELETING... ");
				String fileToWildCard = request.getHeader("fileToWildCard");
				String directory = request.getHeader("directory");
				File dir = new File(directory);
				FileFilter fileFilter = new WildcardFileFilter(fileToWildCard + "*.properties");
				File[] files = dir.listFiles(fileFilter);
				for (File file : files) {
					System.out.println("FILE NAME:  "+file.getName());
					file.delete();
				}
				sb.append("END FILES DELETING... ");
				
			} else { 
				
				sb.append("BEGINNING FILE WRITE...");
				String filename = request.getHeader("filename");
				String path = request.getHeader("directory") + filename;
				String bundlebase = request.getHeader("bundlebasename");
				File propFile = new File(path);
				fos = new FileOutputStream(propFile);
				inputFromClient = request.getInputStream();
				byte[] fileBuffer = new byte[1024];
				int cnt = 0;
				while((cnt=inputFromClient.read(fileBuffer))>-1){
					fos.write(fileBuffer,0,cnt);
				}
				sb.append("FILE WRITTEN");
				
				/*
				 * CLEAR BUNDLE FROM CACHE...
				 */
				/* OLD CODE
				Class klass =   
					  ResourceBundle.getBundle(bundlebase).getClass().getSuperclass();
					      Field field = null;
					      try {
					        field = klass.getDeclaredField("cacheList");
					        System.out.println("CLEARED...1");
					      } catch (NoSuchFieldException noSuchFieldEx) {
					        System.err.println(this.getClass().getName()+" : "
					          +noSuchFieldEx.getMessage());
					      }
					      field.setAccessible(true);  // autoriser l'accès au cache
					      SoftCache cache = null;
					      try {
					        cache = (SoftCache)field.get(null);
					        System.out.println("CACHE NULLED.1");
					      } catch (IllegalAccessException illegalAccessEx) {
					        System.err.println(this.getClass().getName()+" : "
					          +illegalAccessEx.getMessage());
					      }
					      cache.clear();
					      field.setAccessible(false); 
				
				*/
			}
				
				Class type = ResourceBundle.class;
				Field cacheList = null;
				try {
					cacheList = type.getDeclaredField("cacheList");
					System.out.println("CLEARED...1");
				} catch (NoSuchFieldException noSuchFieldEx) {
			        System.err.println(this.getClass().getName()+" : " + noSuchFieldEx.getMessage());
			    }
				cacheList.setAccessible(true); // authorize access to the cache
				
				((Map)cacheList.get(ResourceBundle.class)).clear();
				
				SoftCache cache = null;
				try {
			        cache = (SoftCache)cacheList.get(null);
			        System.out.println("CACHE NULLED.1");
			    } catch (IllegalAccessException illegalAccessEx) {
			        System.err.println(this.getClass().getName()+" : " + illegalAccessEx.getMessage());
			    }
			    cache.clear();
			    cacheList.setAccessible(false); 
				
				/*
				 * PUT THE BUNDLE BACK...
				 * 
				 */
	//			ResourceBundle rb = ResourceBundle.getBundle("GRC_EmailUtilResource",new Locale("en"));
	//			rb.getKeys();
			
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if (inputFromClient!=null)inputFromClient.close();
			if (fos!=null){
				fos.flush();
				fos.close();
			}
			sb.append("CONNECTIONS CLOSED");
		}
		System.out.println(sb.toString());
		response.getWriter().println(sb.toString());
	}

}
