package com.dynamicformgeneration.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dynamicformgeneration.entities.Person;
import com.dynamicformgeneration.entities.PersonValue;
import com.dynamicformgeneration.models.PageableModel;
import com.dynamicformgeneration.models.PersonModel;
import com.dynamicformgeneration.models.PersonValueModel;
import com.dynamicformgeneration.repository.IPersonRepository;
import com.dynamicformgeneration.repository.IPersonValueRepository;
import com.dynamicformgeneration.services.IPersonService;

import javassist.NotFoundException;

@Service
public class PersonService implements IPersonService{

	private final IPersonRepository personRepository;
	private final IPersonValueRepository personValueRepository;
	
	@Autowired
	public PersonService(IPersonRepository personRepository, IPersonValueRepository personValueRepository) {
		this.personRepository = personRepository;
		this.personValueRepository = personValueRepository;
	}


	@Override
	public List<PersonValueModel> create(List<PersonValueModel> values) {
		Person person = new Person();
		person.setCreatedDate(new Date());
		person.setModifiedDate(new Date());
		person = this.personRepository.save(person);
		
		List<PersonValue> PersonValues = new ArrayList<>();
		for (PersonValueModel personValueModel : values) {
			PersonValue value = new PersonValue();
			value.setPersonId(person.getId());
			value.setPropertyId(personValueModel.getPropertyId());
			value.setValue(personValueModel.getValue());
			value.setCreatedDate(new Date());
			value.setModifiedDate(new Date());
			PersonValues.add(value);
		}
		personValueRepository.saveAll(PersonValues);
		
		return values;
	}


	@Override
	public PageableModel getAll(Pageable pageable, String filter) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<PersonValueModel> getByPersonId(int personId) throws NotFoundException {
		List<PersonValueModel> values = new ArrayList<>();
		List<Object[]> personValues = personValueRepository.findAllByPersonId(personId);
		for (Object[] personValue : personValues) {
			PersonValueModel value = new PersonValueModel();
			value.setPersonId(personValue[1] != null ? Integer.valueOf(personValue[1].toString()) : null);
			value.setPropertyId(personValue[2] != null ? Integer.valueOf(personValue[2].toString()) : null);
			value.setValue(personValue[3] != null ? personValue[3].toString() : "");
			values.add(value);
		}
		return values;
	}
	
}
