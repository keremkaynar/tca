package com.tca.dao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tca.dao.access.ContactPersonRepository;
import com.tca.dao.access.TeamRepository;
import com.tca.dao.model.ContactPerson;
import com.tca.dao.model.Team;

@Service
@Transactional
public class TcaDataService {

	@Autowired
	private ContactPersonRepository contactPersonRepository;

	@Autowired
	private TeamRepository teamRepository;

	public ContactPerson findContactPersonById(Integer contactPersonId) {
		return contactPersonRepository.findContactPersonById(contactPersonId);
	}

	public List<ContactPerson> findContactPersonsByTeam(String teamName) {
		return contactPersonRepository.findContactPersonsByTeam(teamName);
	}

	public ContactPerson saveContactPerson(ContactPerson contactPerson) {
		return contactPersonRepository.save(contactPerson);
	}

	public void updateContactPersonById(ContactPerson contactPerson) {
		contactPersonRepository.updateContactPersonById(contactPerson.getFirstName(), contactPerson.getLastName(),
				contactPerson.getMailAddress(), contactPerson.getId());
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

	public void deleteTeam(Integer teamId) {
		teamRepository.delete(teamId);
	}

	public Team createTeam(Team team) {
		return teamRepository.save(team);
	}
}
