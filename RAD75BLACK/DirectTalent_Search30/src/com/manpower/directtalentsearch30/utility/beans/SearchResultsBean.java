package com.manpower.directtalentsearch30.utility.beans;

import java.util.Iterator;

import org.json.JSONArray;

public class SearchResultsBean {
	
//	private ScoreDoc[] scoredocs;
	private Iterator<Object> frequentterms;
	private JSONArray results;
	
	public JSONArray getResults() {
		return results;
	}
	public void setResults(JSONArray results) {
		this.results = results;
	}
//	public ScoreDoc[] getScoredocs() {
//		return scoredocs;
//	}
//	public void setScoredocs(ScoreDoc[] scoredocs) {
//		this.scoredocs = scoredocs;
//	}
	public Iterator<Object> getFrequentterms() {
		return frequentterms;
	}
	public void setFrequentterms(Iterator<Object> frequentterms) {
		this.frequentterms = frequentterms;
	}
	
	
	

}
