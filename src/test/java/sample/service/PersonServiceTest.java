package sample.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import sample.vo.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/datasource.xml",
	"file:src/main/webapp/WEB-INF/spring/hibernate-context.xml"
})
@Transactional
public class PersonServiceTest {
	@Autowired
	PersonService personService;
	
	@Test
	public void test() {
		Person person = new Person("1003", "guest");
		
		
		try {
//			personService.addPerson(person);
//			personService.findAddPerson(person);
//			for (Person p : personService.updateFindPerson(person)) {
//				System.out.println(p);
//			}
			
			for (Person p : personService.findUpdatePerson(person)) {
				System.out.println(p);
			}
			
//			for (Person p : personService.getPersonList()) {
//				System.out.println(p);
//			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getSimpleName());
//			Assert.assertTrue(false);
		}
	}
}
