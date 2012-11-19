/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

/**
 UserException class for handling Exception
 */
public class CustomException extends Exception{
    
    public CustomException(){
        
    }
    public CustomException(String str){
       super(str);
       
    }
}
