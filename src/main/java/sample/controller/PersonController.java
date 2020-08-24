package sample.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import sample.service.PersonService;
import sample.vo.Person;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@RequestMapping(value="/register", method=POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String registerPerson(@RequestBody Person person) {
		personService.addPerson(person);
		return "SUCCESS";
	}
	
	@RequestMapping(value="/update", method=POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String updatePerson(@RequestBody Person person) {
		personService.changePerson(person);
		return "SUCCESS";
	}
	
	
	@RequestMapping(value="/remove", method=POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String removePerson(@RequestBody Person person) {
		personService.removePerson(person);
		return "SUCCESS";
	}
	
	@RequestMapping(value="/{id}", method=GET)
	@ResponseStatus(HttpStatus.OK)
	public Person getPerson(@PathVariable String id) {
		return personService.getPerson(id);
	}
	
	@RequestMapping(value="/list", method=GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Person> getPersonList() {
		return personService.getPersonList();
	}
}
