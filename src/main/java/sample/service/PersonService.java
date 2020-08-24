package sample.service;

import java.util.List;

import sample.vo.Person;

public interface PersonService {
	void addPerson(Person person);
	void changePerson(Person person);
	void removePerson(Person person);
	
	Person getPerson(String id);
	List<Person> getPersonList();
	
	void findAddPerson(Person person);
	List<Person> addFindPerson(Person person);
	
	List<Person> updateFindPerson(Person person);
	List<Person> findUpdatePerson(Person person);
}
