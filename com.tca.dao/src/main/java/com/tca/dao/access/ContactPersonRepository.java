package com.tca.dao.access;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tca.dao.model.ContactPerson;

public interface ContactPersonRepository extends CrudRepository<ContactPerson, Integer> {
	@Query("select c from ContactPerson c where c.team.name like :#{#teamName}")
	List<ContactPerson> findContactPersonsByTeam(@Param("teamName") String teamName);

	ContactPerson findContactPersonById(Integer contactPersonId);

	@Modifying
	@Query("update ContactPerson c set c.firstName = ?1, c.lastName = ?2, c.mailAddress= ?3 where c.id = ?4")
	void updateContactPersonById(String firstname, String lastname, String mailAddress, Integer id);
}
