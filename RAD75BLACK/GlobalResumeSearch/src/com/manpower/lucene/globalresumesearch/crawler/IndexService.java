package com.manpower.lucene.globalresumesearch.crawler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.DOMImplementation;

import com.manpower.hbn.beans.SearchCandidate;
import com.manpower.hbn.shared.HibernateUtilities;
import com.manpower.hbn.shared.dao.DAOFactory;

public class IndexService {
	
	private static IndexService instance = null;
//	private static String INDEX_DIRECTORY = "C:/xxLuceneTest/index5"; //WINDOWS
	private static String INDEX_DIRECTORY = "/lucene2/portalresumes"; //LINUX
	protected IndexService(){
		
	}
	
	/**
	 * @return
	 */
	public static IndexService getInstance(){
		if (instance == null)instance = new IndexService();
		return instance;
	}

	
	/**
	 * @param trigger
	 * @param maxrecords
	 * 
	 * 
	 */

	public void indexAllDTResumes(int trigger,int maxrecords){
		ScrollableResults results = DAOFactory.getDAOFactory().getCandidateResumeInfoDAO().indexAllDocuments();
		int recCount = 0;
		int index = 0;
		List<Object> tempList = new ArrayList<Object>();
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
			if (recCount==maxrecords){
				System.out.println("BREAKING AT RECORD #:  "+recCount);
				break;
			}

		}
		if (index>0){
			CrawlerService.indexDocuments(tempList);
		}
		
	}
	
	public void optimizeIndex(){
		Analyzer analyzer  = new StandardAnalyzer();
		IndexWriter writer=null;
		
		try {
			writer=new IndexWriter(INDEX_DIRECTORY,analyzer,IndexWriter.MaxFieldLength.UNLIMITED);
			writer.optimize();
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
				if (writer!=null)writer.close();
			} catch (CorruptIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		

	}
	
	@SuppressWarnings("unchecked")
	public void indexDocuments(List databaseResults){
		Analyzer analyzer  = new StandardAnalyzer();
		IndexWriter writer = null;
		try {
			writer = new IndexWriter(INDEX_DIRECTORY,analyzer,IndexWriter.MaxFieldLength.UNLIMITED);
			for (int i = 0; i <databaseResults.size();i++){
			  SearchCandidate searchCandidate = (SearchCandidate)databaseResults.get(i);
			  try{
				  writer.addDocument(createDocument(searchCandidate));
			  }catch (OutOfMemoryError ome){
				  //CATCH THE BAD DOCUMENT AND INDEX THE REST OF THE COLLECTION.....
				  //POSSIBLY ADD A ROUTINE TO MAKE SURE THAT THE USER GETS ADDED DESPITE THE BAD RESUME....IndexNoResume()?
				  System.out.println("POSSIBLE BAD DOCUMENT(ResumeId):  "+searchCandidate.getResumeid());
//				  searchCandidate.setResume(null);
//				  writer.addDocument(createDocument(searchCandidate));
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
	
	public void indexDocument(SearchCandidate searchCandidate){
		Analyzer analyzer  = new StandardAnalyzer();
		IndexWriter writer = null;
		try {
			writer = new IndexWriter(INDEX_DIRECTORY,analyzer,IndexWriter.MaxFieldLength.UNLIMITED);
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

	public String queryDocuments(String searchCriteria,String [] queryFields,int page,int recordsPerPage) throws ParseException, CorruptIndexException, IOException{
	  	IndexSearcher searcher = new IndexSearcher(INDEX_DIRECTORY);
	  	
//	  	System.out.println("TOTAL DOCUMENTS INDEXED...."+searcher.getIndexReader().numDocs());
        Analyzer analyzer = new StandardAnalyzer();
        
        MultiFieldQueryParser parser = new MultiFieldQueryParser(queryFields,analyzer);
        Query query = parser.parse(searchCriteria);
//        int recordsPerPage = 16;
        
        int queryLimit = recordsPerPage;
        if (page >0 )queryLimit = queryLimit*page;
        TopDocCollector collector = new TopDocCollector(queryLimit);
        searcher.search(query, collector);
        
        //START  FACETED EXPERIMENT
//        IndexReader reader = searcher.getIndexReader();
//        TermInfoQueue terminfoqueue = new TermInfoQueue(100);
//        TermEnum termenum = reader.terms();
//
//        String [] as = {"resumetext"};
//        Hashtable hashtable = new Hashtable();
//        int j = 0;
//        do
//        {
//            if(!termenum.next())
//                break;
//            String s = termenum.term().field();
//            if(as != null && as.length > 0)
//            {
//                boolean flag = true;
//                int l = 0;
//                do
//                {
//                    if(l >= as.length)
//                        break;
//                    if(s.equals(as[l]))
//                    {
//                        flag = false;
//                        break;
//                    }
//                    l++;
//                } while(true);
//                if(flag)
//                    continue;
//            }
//            if((hashtable == null || hashtable.get(termenum.term().text()) == null) && termenum.docFreq() > j)
//            {
//                terminfoqueue.put(new TermInfo(termenum.term(), termenum.docFreq()));
//                if(terminfoqueue.size() >= 100)
//                {
//                    terminfoqueue.pop();
//                    j = ((TermInfo)terminfoqueue.top()).docFreq;
//                }
//            }
//        } while(true);
//        TermInfo aterminfo[] = new TermInfo[terminfoqueue.size()];
//        for(int k = 0; k < aterminfo.length; k++){
//            aterminfo[aterminfo.length - k - 1] = (TermInfo)terminfoqueue.pop();
//            System.out.println("TERM:  "+aterminfo[aterminfo.length - k - 1].term.text()+"---->"+aterminfo[aterminfo.length - k - 1].docFreq);
//        }
//
//        
//        System.out.println("TERM LIST LENGTH:  "+aterminfo.length);
        //END EXPERIMENT..
        
        
        ScoreDoc[] hits = collector.topDocs().scoreDocs;
       
        StringBuffer sb = new StringBuffer();
//        sb.append("Found " + collector.getTotalHits() + " hits.<hr>");

        int start = queryLimit - recordsPerPage;
        ScoreDoc [] pageHits = new ScoreDoc[recordsPerPage];
        int pageIndex = 0;

        if (queryLimit > collector.getTotalHits()){
        	System.out.println("HERE");
        	queryLimit = collector.getTotalHits();
        	start = (collector.getTotalHits()/recordsPerPage)*recordsPerPage;
        	pageHits = new ScoreDoc[queryLimit-start];
        }
        

        for (int i = start;i<queryLimit;i++){
        	pageHits[pageIndex]=hits[i];
        	pageIndex++;
        }

     
        sb.append(getJSONResults(searcher, pageHits,collector.getTotalHits()));

        searcher.close();
        return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	public String getXMLResults(IndexSearcher searcher,ScoreDoc[]hits) throws ParserConfigurationException, CorruptIndexException, IOException{
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		DOMImplementation impl = builder.getDOMImplementation();

		org.w3c.dom.Document doc = impl.createDocument(null,null,null);
		org.w3c.dom.Element results = doc.createElement("results");
		doc.appendChild(results);
		for (int i=0;i<hits.length;i++){
			org.w3c.dom.Element element = doc.createElement("result");
			Document rDoc = searcher.doc(hits[i].doc);
			List<Field> fieldList = rDoc.getFields();
			for (int x = 0; x<fieldList.size();x++){
				Field field = fieldList.get(x);
				org.w3c.dom.Element childElement = doc.createElement(field.name());
				childElement.setTextContent(rDoc.get(field.name()));
				element.appendChild(childElement);
				
			}
			results.appendChild(element);	
		}
		DOMSource domSource = new DOMSource(doc);
		TransformerFactory tf = TransformerFactory.newInstance();
		java.io.StringWriter sw = new java.io.StringWriter();
		try { 
			Transformer transformer = tf.newTransformer();
			//transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			StreamResult sr = new StreamResult(sw);
			
			transformer.transform(domSource, sr);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String xml = sw.toString();
//		System.out.println(xml);	
		return sw.toString();
	}
	
	@SuppressWarnings("unchecked")
	private JSONArray getJSONResults (IndexSearcher searcher,ScoreDoc[]hits,int totalhits) throws CorruptIndexException, IOException{
		JSONArray jArray = new JSONArray();
		for (int i=0;i<hits.length;i++){
			JSONObject jobj = new JSONObject();
			Document rDoc = searcher.doc(hits[i].doc);
			List<Field> fieldList = rDoc.getFields();
			for (int x = 0; x<fieldList.size();x++){
				Field field = fieldList.get(x);
				try {
					jobj.put(field.name(), rDoc.get(field.name()));
					jobj.put("score", hits[i].score);
					jobj.put("totalresults",Integer.toString(totalhits));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			jArray.put(jobj);
		}
		return jArray;
	}
	
	public void deleteDocument(String keyField,String keyValue) {
		IndexReader reader = null;
		try {
			reader = IndexReader.open(INDEX_DIRECTORY);
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
	
	
	private Document createDocument(SearchCandidate candidateBean) throws OutOfMemoryError{
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
        	System.out.println("RESUME ID:  "+candidateBean.getResumeid());
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
