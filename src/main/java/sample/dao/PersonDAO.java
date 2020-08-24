package sample.dao;

import java.util.List;

import org.hibernate.Session;

import sample.vo.Person;

public interface PersonDAO {
	void insertPersonBySession(Person person);
	
	Person selectPerson(String id);
	
	void insertPerson(Person person);
	
	void updatePerson(Person person);
	
	void deletePerson(Person person);
	
	List<Person> selectAllPerson();
	
}
