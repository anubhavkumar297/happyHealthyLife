package com.happyHealthyLife.service;

import java.util.List;

import com.happyHealthyLife.modal.Contacts;

public interface ContactHelperService {
	
	public void createContact(Contacts contact);
	
	public List<Contacts> searchByName(String name);
	
	public List<Contacts> searchByNumber(String number);
	
	public void updateExistingContactInfo(Contacts contact);
	
	public void addNumberToExistingContact(int id, String number,String type);
	
	public void deleteNumberFromContact(int id);

}
