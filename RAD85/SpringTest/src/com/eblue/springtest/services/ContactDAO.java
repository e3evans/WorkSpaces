package com.eblue.springtest.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.eblue.springtest.beans.Contact;



public interface ContactDAO {
	public ArrayList<Contact> getContactList();
	public Contact getContact(String contactId);
	public int insertContact(Contact contact);
	public int updateContact(Contact contact);
	public int deleteContact(String contactId);
}
