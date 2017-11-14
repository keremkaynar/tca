package com.tca.dao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tca.dao.access.ContactPersonRepository;
import com.tca.dao.access.TeamRepository;
import com.tca.dao.model.ContactPerson;
import com.tca.dao.model.Team;

@Service
public class TcaDataService {

  @Autowired
  private ContactPersonRepository contactPersonRepository;

  @Autowired
  private TeamRepository teamRepository;

  public List<ContactPerson> findContactPersonsByTeam(String teamName) {
    return contactPersonRepository.findContactPersonsByTeam(teamName);
  }

  public ContactPerson saveContactPerson(ContactPerson contactPerson) {
    return contactPersonRepository.save(contactPerson);
  }

  public void deleteContactPerson(Integer contactPersonId) {
    contactPersonRepository.delete(contactPersonId);
  }

  public List<Team> findAllTeams() {
    Iterable<Team> teamsIterable = teamRepository.findAll();
    List<Team> teams = new ArrayList<>();
    teamsIterable.forEach(teams::add);
    return teams;
  }
}
