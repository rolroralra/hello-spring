package sample.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import sample.vo.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={
		"file:src/main/webapp/WEB-INF/spring/hibernate-context.xml"
})
@Transactional
public class PersonDAOTest {
	
	@Autowired
	PersonDAO personDAO;
	
	
	@Test
	public void test() {
		personDAO.insertPersonBySession(new Person("1008", "test1234"));
		List<Person> list = personDAO.selectAllPerson();
		
		for (Person person : list) {
			System.out.println(person);
		}
		
	}
}
