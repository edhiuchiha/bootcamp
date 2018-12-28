package com.sti.bootcamp.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomerDto {
	private int customernumber;
	private String firstname;
	private String lastname;
	private String address;
	private String email;
	private String password;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthdate;
	private String phonenumber;
	private String phonetype;
	
	public CustomerDto() {}
	
	public CustomerDto(int customernumber, String firstname, String lastname,String address,String email,String password,Date birthdate, String phonenumber, String phonetype) {
		this.setCustomernumber(customernumber);
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.email = email;
		this.password = password;
		this.birthdate = birthdate;
		this.phonenumber = phonenumber;
		this.phonetype =  phonetype;
	}

	public int getCustomernumber() {
		return customernumber;
	}

	public void setCustomernumber(int customernumber) {
		this.customernumber = customernumber;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getPhonetype() {
		return phonetype;
	}

	public void setPhonetype(String phonetype) {
		this.phonetype = phonetype;
	}

}
