/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain;

/**
 *
 * @author praveen.ka
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;

/**
 *
 * @author praveen.ka
 */
public class Poll {
    
    private String pollId;
    private String qname;
    private String id;
    private String option;

    /**
     * @return the qname
     */
    public String getQname() {
        return qname;
    }

    /**
     * @param qname the qname to set
     */
    public void setQname(String qname) {
        this.qname = qname;
    }

    /**
     * @return the options
     */
    public String getOption() {
        return option;
    }

    /**
     * @param options the options to set
     */
    public void setOption(String option) {
        this.option = option;
    }

    /**
     * @return the pollId
     */
    public String getPollId() {
        return pollId;
    }

    /**
     * @param pollId the pollId to set
     */
    public void setPollId(String pollId) {
        this.pollId = pollId;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    
}
