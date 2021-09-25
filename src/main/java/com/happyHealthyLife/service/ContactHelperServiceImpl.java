package com.happyHealthyLife.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.happyHealthyLife.modal.Contacts;

public class ContactHelperServiceImpl implements ContactHelperService {
	StandardServiceRegistry ssr;
	Metadata meta;
	SessionFactory factory;
	Session session;
	Transaction tr;

	public ContactHelperServiceImpl() {
		this.ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		this.meta = new MetadataSources(ssr).getMetadataBuilder().build();
		this.factory = meta.getSessionFactoryBuilder().build();
		this.session = factory.openSession();
		this.tr = session.beginTransaction();
	}

	@Override
	public void createContact(Contacts contact) {
		try {
			session.persist(contact);
			this.tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<Contacts> searchByName(String name) {
		String hql = "FROM Contacts contact WHERE contact.first_name = '" + name + "'";
		Query query = session.createQuery(hql);
		List<Contacts> results = ((org.hibernate.query.Query) query).list();
		for (Contacts contact : results) {
			System.out.println(contact.toString());
		}
		session.close();
		return results;
	}

	@Override
	public List<Contacts> searchByNumber(String number) {
		String hql = "FROM Contacts";
		Query query = session.createQuery(hql);
		List<Contacts> results = ((org.hibernate.query.Query) query).list();
		List<Contacts> final_result = new ArrayList<Contacts>();
		for (Contacts contact : results) {
			if(contact.getPhone_numbers().containsValue(number)) {
				final_result.add(contact);
			}
			System.out.println(contact.toString());
		}
		System.out.println(final_result.size());
		session.close();
		return final_result;
	}

	@Override
	public void updateExistingContactInfo(Contacts contact) {
		Contacts contactLoad = session.load(Contacts.class, contact.getId());
		contactLoad.setFirst_name(contact.getFirst_name());
		contactLoad.setLast_name(contact.getLast_name());
		contactLoad.setEmail(contact.getEmail());
		contactLoad.setPhone_numbers(contact.getPhone_numbers());
        session.update(contactLoad);
        tr.commit();
        session.close();
	}

	public void addNumberToExistingContact(int id, String number, String type) {
		Contacts contactLoad = session.load(Contacts.class, id);
		Map<String,String> phone_numbers = contactLoad.getPhone_numbers();
		phone_numbers.put(type,number);
		contactLoad.setPhone_numbers(phone_numbers);
        session.update(contactLoad);
        tr.commit();
        session.close();
	}

	@Override
	public void deleteNumberFromContact(int id) {
		Contacts contactLoad = session.load(Contacts.class, id);
		if(contactLoad != null) {
			session.delete(contactLoad);
			tr.commit();
		}
		session.close();
	}

}
