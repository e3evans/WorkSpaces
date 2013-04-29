package com.manpower.lucene.globalresumesearch.utils;

import org.apache.lucene.index.Term;

public class TermInfo {
	public Term term;
    public int docFreq;
	
    public TermInfo(Term term1, int i)
    {
        term = term1;
        docFreq = i;
    }

    
	
}
