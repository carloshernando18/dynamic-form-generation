package com.dynamicformgeneration.services;

import org.springframework.data.domain.Pageable;

import com.dynamicformgeneration.models.PageableModel;
import com.dynamicformgeneration.models.PersonModel;

import javassist.NotFoundException;

public interface IPersonService {

	PageableModel getAll(Pageable pageable, String filter);

	PersonModel getById(int id) throws NotFoundException;

}
