package com.tca.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tca.dao.model.ContactPerson;
import com.tca.dao.model.Team;
import com.tca.dao.service.TcaDataService;

@Controller
@SessionAttributes("currentTeam")
public class TcaController {
  @Autowired
  private TcaDataService tcaDataService;

  @RequestMapping(value = "/showcontacts", method = RequestMethod.GET)
  public String showContacts(ModelMap attributes, @RequestParam(value = "teamname", required = false, defaultValue = "") String teamName) {
    List<Team> teams = tcaDataService.findAllTeams();
    attributes.addAttribute("teams", teams);
    String queryTeamName = teamName;
    if (queryTeamName.equals("") && !teams.isEmpty()) {
      queryTeamName = teams.get(0).getName();
    }
    Team currentTeam = findCurrentTeamByName(teams, queryTeamName);
    attributes.addAttribute("currentTeam", currentTeam);
    List<ContactPerson> contactPersons = tcaDataService.findContactPersonsByTeam(queryTeamName);
    attributes.addAttribute("contactPersons", contactPersons);
    return "showcontacts";
  }

  @RequestMapping(value = "/createteam", method = RequestMethod.POST, headers = "Accept=application/json")
  public String createTeam(@ModelAttribute(value = "createdTeam") Team team) {
    Team createdTeam = tcaDataService.createTeam(team);
    return "redirect:/showcontacts?" + "teamname=" + createdTeam.getName();
  }

  @RequestMapping(value = "/deleteteam", method = RequestMethod.POST)
  public String deleteTeam(@ModelAttribute(value = "currentTeam") Team currentTeam) {
    if (currentTeam != null) {
      tcaDataService.deleteTeam(currentTeam.getId());
    }
    return "redirect:/showcontacts";
  }

  @RequestMapping(value = "/showcontactdetails", method = RequestMethod.GET)
  public String createUpdateContactPerson(ModelMap attributes,
      @RequestParam(value = "contactpersonid", required = false, defaultValue = "-1") Integer contactPersonId) {
    ContactPerson contactPerson = null;
    if (contactPersonId == -1) {
      Object currentTeamObj = attributes.get("currentTeam");
      if (currentTeamObj != null) {
        Team currentTeam = (Team) currentTeamObj;
        contactPerson = new ContactPerson();
        contactPerson.setId(contactPersonId);
        contactPerson.setTeam(currentTeam);
      }
    } else {
      contactPerson = tcaDataService.findContactPersonById(contactPersonId);
    }
    if (contactPerson == null) {
      return "redirect:/showcontacts";
    }
    attributes.addAttribute("contactPerson", contactPerson);
    return "showcontactdetails";
  }

  @RequestMapping(value = "/editcontact", params = "create", method = RequestMethod.POST, headers = "Accept=application/json")
  public String createContactPerson(ModelMap attributes, @RequestBody ContactPerson contactPerson) {
    ContactPerson createdContactPerson = tcaDataService.saveContactPerson(contactPerson);
    return "redirect:/showcontacts?" + "teamname=" + createdContactPerson.getTeam().getName();
  }

  @RequestMapping(value = "/editcontact", params = "update", method = RequestMethod.POST, headers = "Accept=application/json")
  public String updateContactPerson(@RequestBody ContactPerson contactPerson) {
    ContactPerson updatedContactPerson = tcaDataService.saveContactPerson(contactPerson);
    return "redirect:/showcontacts?" + "teamname=" + updatedContactPerson.getTeam().getName();
  }

  @RequestMapping(value = "/editcontact", params = "delete", method = RequestMethod.POST, headers = "Accept=application/json")
  public String deleteContactPerson(ModelMap attributes, @ModelAttribute(value = "contactPerson") ContactPerson contactPerson) {
    tcaDataService.deleteContactPerson(contactPerson.getId());
    return "redirect:/showcontacts?" + "teamname=" + contactPerson.getTeam().getName();
  }

  private Team findCurrentTeamByName(List<Team> teams, String queryTeamName) {
    for (Team team : teams) {
      if (team.getName().equals(queryTeamName)) {
        return team;
      }
    }
    return null;
  }
}
