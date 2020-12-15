package com.dynamicformgeneration.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "Person", schema="public")
public class Person {
	
	@Id
	@GeneratedValue()
	private Integer id;
	
	@Column
	private Date createdDate;
	
	@Column
	private Date modifiedDate;

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<PersonValue> personValues;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public List<PersonValue> getPersonValues() {
		return personValues;
	}

	public void setPersonValues(List<PersonValue> personValues) {
		this.personValues = personValues;
	}

}
