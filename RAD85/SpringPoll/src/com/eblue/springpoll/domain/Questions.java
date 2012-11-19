/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eblue.springpoll.domain;

public class Questions {
    
    private String question;
    private int questionID;
    private String status ;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }
    
}
