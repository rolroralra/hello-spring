package sample.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import lombok.Setter;
import sample.dao.PersonDAO;
import sample.vo.Person;

@Repository
@Setter
public class HibernatePersonDAO implements PersonDAO {
	
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Deprecated
	@Override
	public void insertPersonBySession(Person person) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.save(person);
			session.flush();
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
			throw new DuplicateKeyException("Primary Key Duplicated!");
		}
		
	}

	@Override
	public void insertPerson(Person person) {
//		hibernateTemplate.setCheckWriteOperations(false);
		hibernateTemplate.save(person);
		hibernateTemplate.flush();
	}
	
	@Override
	public void updatePerson(Person person) {
		hibernateTemplate.update(person);
		hibernateTemplate.flush();
	}

	@Override
	public void deletePerson(Person person) {
		hibernateTemplate.delete(person);
		hibernateTemplate.flush();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Person selectPerson(String id) {
		List<?> list = hibernateTemplate.find("from Person where id=" + id);
		if (list.size() == 0) {
			return null;
		}
		return (Person) list.get(0);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Person> selectAllPerson() {
//		Session session = sessionFactory.getCurrentSession();
//		Query<Person> query = session.createQuery("from Person", Person.class);
//		return query.list();
		
		return (List<Person>) hibernateTemplate.find("from Person");
	}

}
