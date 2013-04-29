package com.manpower.lucene.globalresumesearch.crawler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.StaleReaderException;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocCollector;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.poi.extractor.ExtractorFactory;
import org.hibernate.ScrollableResults;

import com.manpower.hbn.beans.SearchCandidate;
import com.manpower.hbn.shared.HibernateUtilities;
import com.manpower.hbn.shared.dao.DAOFactory;

public class CrawlerService {

	
	@SuppressWarnings("unchecked")
	public static void indexAllDTResumes(int trigger){
		ScrollableResults results = DAOFactory.getDAOFactory().getCandidateResumeInfoDAO().indexAllDocuments();
		int recCount = 0;
		int index = 0;
		List tempList = new ArrayList();
		while (results.next()){
			index++;
			recCount++;
			tempList.add(results.get(0));
			if (index==trigger){
				System.out.println("BEGINNING LUCENE INDEX PROCESS");
				CrawlerService.indexDocuments(tempList);
				System.out.println("END LUCENE INDEX PROCESS");
				index=0;
				tempList.clear();
				HibernateUtilities.currentSession().clear();
			}
			System.out.println("Indexing Record#  "+recCount);

		}
		if (index>0){
			CrawlerService.indexDocuments(tempList);
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public static void indexDocuments(List databaseResults){
		Analyzer analyzer  = new StandardAnalyzer();
		IndexWriter writer = null;
		try {
			writer = new IndexWriter("C:/xxLuceneTest/index",analyzer,IndexWriter.MaxFieldLength.UNLIMITED); //WINDOWS
//			writer = new IndexWriter("/lucene2/index5",analyzer,IndexWriter.MaxFieldLength.UNLIMITED); //LINUX
			for (int i = 0; i <databaseResults.size();i++){
			  SearchCandidate searchCandidate = (SearchCandidate)databaseResults.get(i);
			  try{
				  writer.addDocument(createDocument(searchCandidate));
			  }catch (OutOfMemoryError ome){
				  //CATCH THE BAD DOCUMENT AND INDEX THE REST OF THE COLLECTION.....
				  //POSSIBLY ADD A ROUTINE TO MAKE SURE THAT THE USER GETS ADDED DESPITE THE BAD RESUME....IndexNoResume()?
				  System.out.println("POSSIBLE BAD DOCUMENT(ResumeId):  "+searchCandidate.getResumeid());
			  }
			}
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if (writer !=null){
					writer.optimize();
				    writer.close();
					writer.close();
				}
				System.out.println("WRITER CLOSED...");
			} catch (CorruptIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public static void indexDocument(SearchCandidate searchCandidate){
		Analyzer analyzer  = new StandardAnalyzer();
		IndexWriter writer = null;
		try {
//			writer = new IndexWriter("C:/xxLuceneTest/index",analyzer,IndexWriter.MaxFieldLength.UNLIMITED); //WINDOWS
			writer = new IndexWriter("/lucene2/index5",analyzer,IndexWriter.MaxFieldLength.UNLIMITED); //LINUX
			writer.addDocument(createDocument(searchCandidate));		
		} catch (OutOfMemoryError e){
			System.out.println("POSSIBLE BAD DOCUMENT(ResumeId):  "+searchCandidate.getResumeid());
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if (writer !=null){
					writer.optimize();
				    writer.close();
					writer.close();
				}
			} catch (CorruptIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	public static String queryDocuments(String searchCriteria,String [] queryFields) throws ParseException, CorruptIndexException, IOException{
	  	IndexSearcher searcher = new IndexSearcher("C:/xxLuceneTest/index2");
	  	
	  	System.out.println("TOTAL DOCUMENTS INDEXED...."+searcher.getIndexReader().numDocs());
        Analyzer analyzer = new StandardAnalyzer();
        
        MultiFieldQueryParser parser = new MultiFieldQueryParser(queryFields,analyzer);
        Query query = parser.parse(searchCriteria);
        int hitsPerPage = 1000;
        
        TopDocCollector collector = new TopDocCollector(hitsPerPage);
        searcher.search(query, collector);
        ScoreDoc[] hits = collector.topDocs().scoreDocs;
        StringBuffer sb = new StringBuffer();
        sb.append("Found " + hits.length + " hits.<hr>");
        
        for(int i=0;i<hits.length;++i) {
          int docId = hits[i].doc;
          Document d = searcher.doc(docId);
          sb.append((i + 1) + ". " + d.get("resumename")+"-->"+d.get("candidateid")+"---->"+d.get("email")+"<br>");
        }
        searcher.close();
        return sb.toString();
	}
	
	public static void deleteDocument(String keyField,String keyValue) {
		IndexReader reader = null;
		try {
			reader = IndexReader.open("C:/xxLuceneTest/index");
			reader.deleteDocuments(new Term(keyField,keyValue));
			reader.close();
		} catch (StaleReaderException e) {
			e.printStackTrace();
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (reader!=null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
	}
	
	
	public static Document createDocument(SearchCandidate candidateBean) throws OutOfMemoryError{
		Document document = new Document();
        if (candidateBean.getCorrespondemail()!=null)document.add(new Field("correspondemail", candidateBean.getCorrespondemail(),Field.Store.YES,Field.Index.ANALYZED));
        if (candidateBean.getEmail()!=null)document.add(new Field("email", candidateBean.getEmail(),Field.Store.YES,Field.Index.ANALYZED));
        if (candidateBean.getFirstname()!=null)document.add(new Field("firstname", candidateBean.getFirstname(),Field.Store.YES,Field.Index.ANALYZED));
        if (candidateBean.getLastname()!=null)document.add(new Field("lastname", candidateBean.getLastname(),Field.Store.YES,Field.Index.ANALYZED));
        if (candidateBean.getMiddlename()!=null)document.add(new Field("middlename", candidateBean.getMiddlename(),Field.Store.YES,Field.Index.ANALYZED));
        if (candidateBean.getNationality()!=null)document.add(new Field("nationality", candidateBean.getNationality(),Field.Store.YES,Field.Index.ANALYZED));
        if (candidateBean.getNationalnumber()!=null)document.add(new Field("nationalnumber", candidateBean.getNationalnumber(),Field.Store.YES,Field.Index.ANALYZED));
        if (candidateBean.getNativelanguage()!=null)document.add(new Field("nativelanguage", candidateBean.getNativelanguage(),Field.Store.YES,Field.Index.ANALYZED));
        if (candidateBean.getResumename()!=null)document.add(new Field("resumename", candidateBean.getResumename(),Field.Store.YES,Field.Index.ANALYZED));
        document.add(new Field("resumeid", Long.toString(candidateBean.getResumeid()),Field.Store.YES,Field.Index.ANALYZED));
        document.add(new Field("candidateid", Long.toString(candidateBean.getId()),Field.Store.YES,Field.Index.ANALYZED));
       
        if (candidateBean.getResumeid()>0 && candidateBean.getMimetype().indexOf("msword")>0 && candidateBean.getFileAsByteArray()!=null){
//        	System.out.println("RESUME ID:  "+candidateBean.getResumeid());
        	InputStream stream = new ByteArrayInputStream(candidateBean.getFileAsByteArray());
    		StringBuffer temp = new StringBuffer();
    		try{
    			temp.append(ExtractorFactory.createExtractor(stream).getText());
    		}catch (Exception e){
    			System.out.println("EXTRACTOR ERROR(Resume Id):  "+candidateBean.getResumeid());
    		}finally{
    			try {
					if (stream!=null)stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
			document.add(new Field("resumetext",temp.toString(),Field.Store.NO,Field.Index.ANALYZED));
	
        }

		return document;
	}
}
