package com.happyHealthyLife.modal;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

@Entity
@Table(name = "contact_info")
public class Contacts {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "first_name")
	private String first_name;
	
	@Column(name = "last_name")
	private String last_name;
	
	@Column(name = "email")
	private String email;
	
	@ElementCollection
    @MapKeyColumn(name="type")
    @Column(name="phone_number")
    @CollectionTable(name="phone_numbers", joinColumns=@JoinColumn(name="id"))
	private Map<String, String> phone_numbers;

	public Contacts() {
	}

	/**
	 * @param first_name
	 * @param last_name
	 * @param email
	 * @param phone_numbers
	 */
	public Contacts(String first_name, String last_name, String email, Map<String, String> phone_numbers) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phone_numbers = phone_numbers;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone_numbers
	 */
	public Map<String, String> getPhone_numbers() {
		return phone_numbers;
	}

	/**
	 * @param phone_numbers the phone_numbers to set
	 */
	public void setPhone_numbers(Map<String, String> phone_numbers) {
		this.phone_numbers = phone_numbers;
	}

	@Override
	public String toString() {
		return "Contacts [first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ ", phone_numbers=" + phone_numbers + "]";
	}

}
