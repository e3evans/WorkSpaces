package com.manpower.directtalentsearch30.utility;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.manpower.directtalentsearch30.hbn.beans.DirectTalentAdvertBean;
import com.manpower.directtalentsearch30.hbn.beans.SiteNameBean;
import com.manpower.directtalentsearch30.hbn.shared.dao.DAOFactory;
@SuppressWarnings("unchecked")
public class IndexerUtility {
	
	public static void IndexAllAdverts() throws IOException{
		List siteList = DAOFactory.getDAOFactory().getDirectTalentAdSearchDAO().findAllSites();
		for (int i = 0;i<siteList.size();i++){
			SiteNameBean site = (SiteNameBean)siteList.get(i);
			List ads = DAOFactory.getDAOFactory().getDirectTalentAdSearchDAO().finaAllBySiteName(site.getSitename());
			Directory d = FSDirectory.open(new File("C:/xxLuceneTest/portaladverts/"+site.getSitename()));
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);
			IndexWriter writer = null;
			writer = new IndexWriter(d,analyzer,MaxFieldLength.UNLIMITED);
			try{
				for (int x=0;x<ads.size();x++){		
					DirectTalentAdvertBean dab = (DirectTalentAdvertBean)ads.get(x);
					writer.addDocument(dab.getLuceneDocument());
					System.out.println("ADDED DOC:  "+site.getSitename()+":"+x);
					
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if (writer!=null){
					writer.optimize();
					writer.close();
				}
			}
		}
		
	}

}
