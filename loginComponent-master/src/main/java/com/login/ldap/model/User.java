package com.login.ldap.model;

import javax.persistence.*;

@Entity
@Table(name = "user_details")
public class User {

	@Id
	@Column(name = "user_id", nullable=false, unique=true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name="user_password")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "name")
	private String name;

	@Column(name = "contact")
	private String phoneNumber;

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    @Column(name = "address")
	private String address;

	@Column(name = "email")
	private String email;

	@Column(name="user_type")
    private String user_type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
// user_type INT


}
