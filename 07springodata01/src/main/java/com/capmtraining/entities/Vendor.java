package com.capmtraining.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.annotation.edm.EdmKey;
import org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty;
import org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty.Multiplicity;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;
import org.apache.olingo.odata2.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@EdmEntitySet
@EdmEntityType
@Entity
@Table(name="VENDOR")
public class Vendor {

	@Id
	@Column(nullable=false, name="ID")
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue(generator="uuid2")
	@GenericGenerator(name="uuid2", strategy="org.hibernate.id.UUIDGenerator")
	@EdmKey
	@EdmProperty
	public String id;
	//public Long id;
	@Column(nullable=false, name="COMPANY_NAME")
	@EdmProperty
	public String companyName;
	@Column(nullable=false, name="FIRST_NAME")
	@EdmProperty
	public String firstName;
	@Column(nullable=false, name="LAST_NAME")
	@EdmProperty
	public String lastName;
	@Column(nullable=false, name="WEBSITE")
	@EdmProperty
	public String website;
	@Column(nullable=false, name="EMAILID")
	@EdmProperty
	public String email;
	@Column(nullable=false, name="STATUS")
	@EdmProperty
	public String status;
	@Column(nullable=false, name="GST_NO")
	@EdmProperty
	public String regDate;

	//TO TEST: http://localhost:8080/vendor/bc44f9bd-4576-41a7-9141-4a7ca547819b
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)

	@JoinColumn(name="vendor", referencedColumnName = "ID") 
	@EdmNavigationProperty(toType=Address.class, toMultiplicity=Multiplicity.MANY)
	private List<Address> addresses = new ArrayList<>();


	public List<Address> getAddresses() { 
		return addresses; 
	}

	public void setAddresses(List<Address> addresses) { 
		this.addresses = addresses; 
	}

	public Vendor() {

	}

	public Vendor(String id, String companyName, String firstName, String lastName, String website, String email,
			String status, String regDate) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.website = website;
		this.email = email;
		this.status = status;
		this.regDate = regDate;
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}


}
