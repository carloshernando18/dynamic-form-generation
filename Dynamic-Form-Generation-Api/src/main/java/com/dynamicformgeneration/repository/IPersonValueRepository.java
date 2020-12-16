package com.dynamicformgeneration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dynamicformgeneration.entities.PersonValue;

@Repository
public interface IPersonValueRepository extends JpaRepository<PersonValue, Integer> {

	@Query("SELECT pv.id, pv.personId,  p.id, pv.value FROM PersonValue pv LEFT JOIN Property p ON p.id = pv.propertyId WHERE pv.personId = ?1 ORDER BY p.orderProperty")
	List<Object[]> findAllByPersonId(int personId);

}
