package com.tca.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tca.dao.model.ContactPerson;
import com.tca.dao.model.Team;
import com.tca.dao.service.TcaDataService;

@RestController
public class TcaController {
  @Autowired
  private TcaDataService tcaDataService;

  @RequestMapping(value = "/getallteams", method = RequestMethod.GET, headers = "Accept=application/json")
  public List<Team> getAllTeams() {
    return tcaDataService.findAllTeams();
  }
  
  @RequestMapping(value = "/{teamName}/getallcontacts", method = RequestMethod.GET, headers = "Accept=application/json")
  public List<ContactPerson> getAllContactPersons(@PathVariable("teamName") String teamName) {
    return tcaDataService.findContactPersonsByTeam(teamName);
  }

  @RequestMapping(value = "/addcontact", method = RequestMethod.POST, headers = "Accept=application/json")
  public ContactPerson addContactPerson(@RequestBody ContactPerson contactPerson) {
    return tcaDataService.saveContactPerson(contactPerson);
  }

  @RequestMapping(value = "/updatecontact", method = RequestMethod.PUT, headers = "Accept=application/json")
  public ContactPerson updateContactPerson(@RequestBody ContactPerson contactPerson) {
    return tcaDataService.saveContactPerson(contactPerson);
  }

  @RequestMapping(value = "/deletecontact/{contactPersonId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
  public void deleteContactPerson(@PathVariable("contactPersonId") Integer contactPersonId) {
    tcaDataService.deleteContactPerson(contactPersonId);
  }
}
