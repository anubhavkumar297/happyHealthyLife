package com.happyHealthyLife.controller;

import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.happyHealthyLife.modal.Contacts;
import com.happyHealthyLife.service.ContactHelperServiceImpl;

@Controller
public class ContactsController {

	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping(value="/addContact", method=RequestMethod.POST)
	String addContact(@RequestBody Contacts contact) {
		ContactHelperServiceImpl impl = new ContactHelperServiceImpl();
		impl.createContact(contact);
		return "Contact Added" + contact.getFirst_name();
	}
	
	@RequestMapping(value="/getContactByName", method=RequestMethod.POST)
	List<Contacts> getContactByName(@RequestBody String name) {
		ContactHelperServiceImpl impl = new ContactHelperServiceImpl();
		List<Contacts> contacts = impl.searchByName(name);
		return contacts;
	}
	
	@RequestMapping(value="/getContactByPhoneNumber", method=RequestMethod.POST)
	List<Contacts> getContactByPhoneNumber(@RequestBody String phone_number) {
		ContactHelperServiceImpl impl = new ContactHelperServiceImpl();
		List<Contacts> contacts = impl.searchByNumber(phone_number);
		return contacts;
	}
	
	@RequestMapping(value="/updateExistingContactInfo", method=RequestMethod.POST)
	String updateExistingContactInfo(@RequestBody Contacts contact) {
		ContactHelperServiceImpl impl = new ContactHelperServiceImpl();
		impl.updateExistingContactInfo(contact);
		return "updated";
	}
	
	@RequestMapping(value="/addNumberToExistingContact", method=RequestMethod.POST)
	String addNumberToExistingContact(@RequestBody int id, String phone_number, String type) {
		ContactHelperServiceImpl impl = new ContactHelperServiceImpl();
		impl.addNumberToExistingContact(id, phone_number, type);
		return "Updated One Contact";
	}

	@RequestMapping(value="/deleteNumberFromContact", method=RequestMethod.POST)
	String deleteNumberFromContact(@RequestBody int id) {
		ContactHelperServiceImpl impl = new ContactHelperServiceImpl();
		impl.deleteNumberFromContact(id);
		return "Deleted One Contact";
	}
	
}
