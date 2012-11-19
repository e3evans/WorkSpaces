/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain;

/**
 *
 * @author praveen.ka
 */
public class VoteCount{

    private int optionId;
    private int count;
    private int pollId;
    private String questionName;
    private String optionName;
     

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public int getPollId() {
        return pollId;
    }

    public void setPollId(int pollId) {
        this.pollId = pollId;
    }
    
    
}
