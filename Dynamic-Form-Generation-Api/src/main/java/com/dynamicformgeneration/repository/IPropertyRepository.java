package com.dynamicformgeneration.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dynamicformgeneration.entities.Property;

@Repository
public interface IPropertyRepository extends JpaRepository<Property, Integer> {

	Page<Property> findAllByNameContainingIgnoreCaseOrTypePropertyContainingIgnoreCase(String name, String typeProperty,
			Pageable pageable);

}
