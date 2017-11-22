package com.tca.webapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
  public String showContacts(ModelMap attributes,
      @RequestParam(value = "teamname", required = false, defaultValue = "") String teamName) {
    List<Team> teams = tcaDataService.findAllTeams();
    attributes.addAttribute("teams", teams);
    String queryTeamName = teamName;
    if (queryTeamName.equals("") && !teams.isEmpty()) {
      queryTeamName = teams.get(0).getName();
    }
    Team currentTeam = findCurrentTeamByName(teams, queryTeamName);
    if (currentTeam != null) {
      attributes.addAttribute("currentTeam", currentTeam);
    } else {
      attributes.addAttribute("currentTeam", new Team());
    }
    attributes.addAttribute("createdTeam", new Team());
    List<ContactPerson> contactPersons = tcaDataService.findContactPersonsByTeam(queryTeamName);
    attributes.addAttribute("contactPersons", contactPersons);
    return "showcontacts";
  }

  @RequestMapping(value = "/createteam", method = RequestMethod.POST, headers = "Accept=application/json")
  public String createTeam(@ModelAttribute("createdTeam") Team team) {
    Team createdTeam = tcaDataService.createTeam(team);
    return "redirect:/showcontacts?" + "teamname=" + createdTeam.getName();
  }

  @RequestMapping(value = "/deleteteam", method = RequestMethod.POST)
  public String deleteTeam(@ModelAttribute("currentTeam") Team currentTeam) {
    tcaDataService.deleteTeam(currentTeam.getId());
    return "redirect:/showcontacts";
  }

  @RequestMapping(value = "/showcontactdetails", method = RequestMethod.GET)
  public String showContactDetails(ModelMap attributes,
      @RequestParam(value = "contactpersonid", required = false, defaultValue = "-1") Integer contactPersonId) {
    ContactPerson contactPerson = null;
    if (contactPersonId == -1) {
      Team currentTeam = (Team) attributes.get("currentTeam");
      if (currentTeam != null
          && currentTeam.getName() != null && !currentTeam.getName().equals("")) {
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

  @RequestMapping(value = "/editcontact", params = "create", method = RequestMethod.POST)
  public String createContactPerson(@ModelAttribute("currentTeam") Team currentTeam,
      @Valid @ModelAttribute("contactPerson") ContactPerson contactPerson, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "showcontactdetails";
    }
    contactPerson.setTeam(currentTeam);
    ContactPerson createdContactPerson = tcaDataService.saveContactPerson(contactPerson);
    return "redirect:/showcontacts?" + "teamname=" + createdContactPerson.getTeam().getName();
  }

  @RequestMapping(value = "/editcontact", params = "update", method = RequestMethod.POST)
  public String updateContactPerson(@ModelAttribute("currentTeam") Team currentTeam,
      @Valid @ModelAttribute("contactPerson") ContactPerson contactPerson, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "showcontactdetails";
    }
    contactPerson.setTeam(currentTeam);
    tcaDataService.updateContactPersonById(contactPerson);
    return "redirect:/showcontacts?" + "teamname=" + contactPerson.getTeam().getName();
  }

  @RequestMapping(value = "/editcontact", params = "delete", method = RequestMethod.POST)
  public String deleteContactPerson(@ModelAttribute("currentTeam") Team currentTeam,
      @ModelAttribute("contactPerson") ContactPerson contactPerson) {
    contactPerson.setTeam(currentTeam);
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
