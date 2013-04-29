package com.manpower.lucene.servlets;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.hibernate.ScrollableResults;

import com.manpower.hbn.beans.SearchCandidate;
import com.manpower.hbn.shared.HibernateUtilities;
import com.manpower.hbn.shared.dao.DAOFactory;
import com.manpower.lucene.globalresumesearch.crawler.CrawlerService;

/**
 * Servlet implementation class Indexer
 */
public class Indexer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Indexer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("STARTING INDEXING!!");
//		Directory d = FSDirectory.open(new File("C:/xxLuceneTest/portalresumes"));
		Directory d = FSDirectory.open(new File("/lucene2/portalresumes"));
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);
		IndexWriter writer = null;
		writer = new IndexWriter(d,analyzer,MaxFieldLength.UNLIMITED);
		writer.setMaxBufferedDocs(100);
		writer.setMaxMergeDocs(100);
		writer.setMergeFactor(20);
		writer.setRAMBufferSizeMB(64);
		ScrollableResults sr = DAOFactory.getDAOFactory().getCandidateResumeInfoDAO().indexAllDocuments();
		int count = 0;
		try{
			while (sr.next()){
				count ++;
				
				SearchCandidate sc = (SearchCandidate)sr.get(0);
				System.out.println("INDEXING DOCUMENT:  "+count +" : "+sc.getResumeid());
				try{
					writer.addDocument(CrawlerService.createDocument(sc));
				}catch(OutOfMemoryError oom){
					System.out.println("CANDIDATE: "+sc.getId()+"--->Out of Memory Error!");
				}
				HibernateUtilities.currentSession().evict(sc);
			}
		}catch(Exception e){
			
		}finally{
			if (writer!=null){
				writer.optimize();
				writer.close();
			}
		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
