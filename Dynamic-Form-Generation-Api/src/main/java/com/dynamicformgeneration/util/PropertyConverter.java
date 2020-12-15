package com.dynamicformgeneration.util;

import java.util.ArrayList;
import java.util.List;

import com.dynamicformgeneration.entities.Property;
import com.dynamicformgeneration.models.PropertyModel;

public class PropertyConverter {
	
	public static Property toProperty(PropertyModel model) {
		Property obj = new Property();
		obj.setId(model.getId());
		obj.setName(model.getName());
		obj.setTypeProperty(model.getTypeProperty());
		obj.setOrderProperty(model.getOrderProperty());
		obj.setMaxLength(model.getMaxLength());
		obj.setPattern(model.getPattern());
		obj.setRequired(model.isRequired());
		obj.setOptions(model.getOptions());
		obj.setPlaceholder(model.getPlaceholder());
		return obj;
	}
	
	public static PropertyModel toPropertyModel(Property property) {
		PropertyModel model = new PropertyModel();
		model.setId(property.getId());
		model.setName(property.getName());
		model.setTypeProperty(property.getTypeProperty());
		model.setOrderProperty(property.getOrderProperty());
		model.setMaxLength(property.getMaxLength());
		model.setPattern(property.getPattern());
		model.setRequired(property.isRequired());
		model.setOptions(property.getOptions());
		model.setPlaceholder(property.getPlaceholder());
		return model;
	}
	
	public static List<Property> toPropertyList(List<PropertyModel> listModels) {
		List<Property> listEntity = new ArrayList<>();
		if (listModels != null) {
			for (PropertyModel property : listModels) {
				listEntity.add(toProperty(property));
			}
		}
		return listEntity;
	}

	public static List<PropertyModel> toPropertyModelList(List<Property> listEntities) {
		List<PropertyModel> listModel = new ArrayList<>();
		if (listEntities != null) {
			for (Property property : listEntities) {
				listModel.add(toPropertyModel(property));
			}
		}
		return listModel;
	}

}
