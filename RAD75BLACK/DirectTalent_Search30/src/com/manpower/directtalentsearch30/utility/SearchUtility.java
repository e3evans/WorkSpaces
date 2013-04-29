package com.manpower.directtalentsearch30.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.TermFreqVector;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.manpower.directtalentsearch30.utility.beans.SearchResultsBean;
@SuppressWarnings("unchecked")
public class SearchUtility {
	
	private static SearchUtility instance = null;
	private static String INDEX_DIRECTORY = "C:/xxLuceneTest/portaladverts/USCampus";
	
	
	
	public static SearchUtility getInstance() {
		if (instance==null)instance = new SearchUtility();
		return instance;
	}
	
	
	/**
	 * @param searchCriteria
	 * @param searchFields
	 * @param page
	 * @param recordsperpage
	 * @return List of Scored documents limited by number of pages specified.
	 */
	public SearchResultsBean findMatchingAdverts(String searchCriteria,String[] searchFields, int page, int recordsperpage){
		ScoreDoc [] pageHits = null;
		SearchResultsBean results = new SearchResultsBean();
		IndexSearcher indexsearch = null;
		try {
			Directory d = FSDirectory.open(new File(INDEX_DIRECTORY));
			indexsearch = new IndexSearcher(d,true);
		
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);
			MultiFieldQueryParser parser = new MultiFieldQueryParser(searchFields,analyzer);
	        Query query = parser.parse(searchCriteria);
			
			
			int queryLimit = recordsperpage;
	        if (page >0 )queryLimit = queryLimit*page;
	        TopScoreDocCollector tsd = TopScoreDocCollector.create(queryLimit, true);
	        indexsearch.search(query, tsd);
	        
	        
	        
	        int start = queryLimit - recordsperpage;
	        ScoreDoc [] hits = tsd.topDocs().scoreDocs;
	        System.out.println(tsd.getTotalHits());
	        pageHits = new ScoreDoc[recordsperpage];

	        if (queryLimit > tsd.getTotalHits()){
	        	queryLimit = tsd.getTotalHits();
	        	start = (tsd.getTotalHits()/recordsperpage)*recordsperpage;
	        	pageHits = new ScoreDoc[queryLimit-start];
	        }
	        int pageIndex = 0;
	        
	        
	        for (int i = start;i<queryLimit;i++){
	        	pageHits[pageIndex]=hits[i];	
	        	pageIndex++;
	        }
	        results.setResults(getJSONResults(indexsearch, pageHits, tsd.getTotalHits()));
	        
	        //COLLECTS MOST FREQUENTYLY USED TERMS.  FACETED SEARCHING???
	        int termCount = 0;
	        Hashtable termHash = new Hashtable();
	        System.out.println(hits.length);
//	        for (int i=0;i<tsd.getTotalHits();i++){
//	        	TermFreqVector tfv = indexsearch.getIndexReader().getTermFreqVector(hits[i].doc, "contracttype");
//	        	String [] test = tfv.getTerms();
//	        	for (int y=0;y<test.length;y++){
//	        		if (termHash.get(test[y])!=null){
//	        			termCount = Integer.parseInt(termHash.get(test[y]).toString());
//	        			termHash.put(test[y], termCount+1);
//	        		}else{
//	        			termHash.put(test[y], 1);
//	        		}	
//	        	}
//	        }
	        
	        

//	        //Put keys and values in to an arraylist using entryset
//	        ArrayList myArrayList=new ArrayList(termHash.entrySet());
//	        //Sort the values based on values first and then keys.
//	        Collections.sort(myArrayList, new MyComparator());
//	        //Store sorted results...
//	        results.setFrequentterms(myArrayList.iterator());
//	        
//	        Iterator itr=myArrayList.iterator();
//	        String key="";
//	        int value=0;
//	        int cnt=0;
//	        while(itr.hasNext()){
//
//		        cnt++;
//		        Map.Entry e=(Map.Entry)itr.next();
//	
//		        key = (String)e.getKey();
//		        value = ((Integer)e.getValue()).intValue();
//	
//		        System.out.println(key+", "+value);
//	        }
	        
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (indexsearch!=null){
				try {
					indexsearch.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return results;
		
		
		
		
	}
	
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
					e.printStackTrace();
				}
				
			}
			jArray.put(jobj);
		}
		return jArray;
	}
	
	class MyComparator implements Comparator{

		public int compare(Object obj1, Object obj2){

			int result=0;Map.Entry e1 = (Map.Entry)obj1 ;
	
			Map.Entry e2 = (Map.Entry)obj2 ;//Sort based on values.
	
			Integer value1 = (Integer)e1.getValue();
			Integer value2 = (Integer)e2.getValue();
	
			if(value1.compareTo(value2)==0){
	
			String word1=(String)e1.getKey();
			String word2=(String)e2.getKey();
	
			//Sort String in an alphabetical order
			result=word1.compareToIgnoreCase(word2);
	
			} else{
			//Sort values in a descending order
			result=value2.compareTo( value1 );
			}
	
			return result;
		}

	}
	


}
