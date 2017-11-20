package com.tca.dao.access;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tca.dao.model.ContactPerson;

public interface ContactPersonRepository extends CrudRepository<ContactPerson, Integer> {
  @Query("select * from ContactPerson where team.name like :#{#teamName}")
  List<ContactPerson> findContactPersonsByTeam(@Param("teamName") String teamName);

  ContactPerson findContactPersonById(Integer contactPersonId);
}
