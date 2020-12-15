package com.dynamicformgeneration.models;

import javax.validation.constraints.NotNull;


public class PropertyModel {
	
	private Integer id;
	
	@NotNull
	private String name;

	@NotNull
	private String typeProperty;

	@NotNull
	private int orderProperty;

	private boolean isRequired;
	
	private Integer maxLength;
	
	private String pattern;
	
	private String options;
	
	private String placeholder;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeProperty() {
		return typeProperty;
	}

	public void setTypeProperty(String typeProperty) {
		this.typeProperty = typeProperty;
	}

	public int getOrderProperty() {
		return orderProperty;
	}

	public void setOrderProperty(int orderProperty) {
		this.orderProperty = orderProperty;
	}

	public boolean isRequired() {
		return isRequired;
	}

	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
	

}
