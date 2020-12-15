package com.dynamicformgeneration.models;

import java.util.List;

public class PersonModel {
	
	private Integer id;
	
	private List<PersonValueModel> personValues;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<PersonValueModel> getPersonValues() {
		return personValues;
	}

	public void setPersonValues(List<PersonValueModel> personValues) {
		this.personValues = personValues;
	}

	
}
