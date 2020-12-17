package com.dynamicformgeneration.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dynamicformgeneration.entities.Person;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Integer> {

	@Query(value = 
			" SELECT "
			+ "	p.id, v.value, p.created_date \r\n" + 
			" FROM person p \r\n" + 
			" INNER JOIN person_value v ON v.person_id = p.id \r\n" + 
			" INNER JOIN property pr ON pr.id = v.property_Id \r\n" + 
			" WHERE \r\n" + 
			"	pr.id = 19 "
			+ "	AND UPPER(v.value) LIKE UPPER(?1) ", countQuery = "SELECT COUNT(1) \r\n" + 
					" FROM person p \r\n" + 
					" INNER JOIN person_value v ON v.person_id = p.id \r\n" + 
					" INNER JOIN property pr ON pr.id = v.property_Id \r\n" + 
					" WHERE \r\n" + 
					"	pr.id = 19 "
					+ " AND UPPER(v.value) LIKE UPPER(?1) ", nativeQuery = true)
	Page<Object[]> findAllPagination(Pageable pageable, String filter);
}

