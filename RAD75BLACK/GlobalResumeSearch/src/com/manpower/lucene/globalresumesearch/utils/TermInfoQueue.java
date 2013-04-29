package com.manpower.lucene.globalresumesearch.utils;

import org.apache.lucene.util.PriorityQueue;

public final class TermInfoQueue extends PriorityQueue {

 	public TermInfoQueue(int i){
        initialize(i);
    }

    protected final boolean lessThan(Object obj, Object obj1){
        TermInfo terminfo = (TermInfo)obj;
        TermInfo terminfo1 = (TermInfo)obj1;
        return terminfo.docFreq < terminfo1.docFreq;
    }

}
