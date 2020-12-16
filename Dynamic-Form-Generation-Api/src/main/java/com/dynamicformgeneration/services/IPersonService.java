package com.dynamicformgeneration.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.dynamicformgeneration.models.PageableModel;
import com.dynamicformgeneration.models.PersonModel;
import com.dynamicformgeneration.models.PersonValueModel;

import javassist.NotFoundException;

public interface IPersonService {

	PageableModel getAll(Pageable pageable, String filter);

	List<PersonValueModel> getByPersonId(int personId) throws NotFoundException;
	
	List<PersonValueModel> create(List<PersonValueModel> values);

}
