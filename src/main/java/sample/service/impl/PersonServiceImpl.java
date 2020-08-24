package sample.service.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.dao.PersonDAO;
import sample.service.PersonService;
import sample.vo.Person;

@Service
public class PersonServiceImpl implements PersonService {
	private Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);
	
	@Autowired(required=false)
	private PersonDAO personDAO;
	
	private static SessionFactory sessionFactory = null;
	
	private Session getSession() throws HibernateException {
		SessionFactory sessionFactory = getSessionFactory();
		return sessionFactory.openSession();
	}
	
	private static synchronized SessionFactory getSessionFactory() throws HibernateException {
		if (sessionFactory == null) {
			Configuration cfg = new Configuration().configure();
			sessionFactory = cfg.buildSessionFactory();
		}
		sessionFactory.getCurrentSession();
		return sessionFactory;
	}
	
	@Deprecated
	public void addPersonBySession(Person person) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			personDAO.insertPersonBySession(person);
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			
			if (transaction != null) {
				try {
					transaction.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
					LOGGER.error("System Error!");
				}
			}
			
			throw e;
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error("System Error!");
				}
				
			}
		}
	}
	
	@Override
	public void addPerson(Person person) {
		personDAO.insertPerson(person);
	}
	
	@Override
	public Person getPerson(String id) {
		return personDAO.selectPerson(id);
	}

	@Override
	public void changePerson(Person person) {
		personDAO.updatePerson(person);
	}

	@Override
	public void removePerson(Person person) {
		personDAO.deletePerson(person);
	}

	@Override
	public List<Person> getPersonList() {
		return personDAO.selectAllPerson();
	}
	
	@Override
	public void findAddPerson(Person person) {
		personDAO.selectAllPerson();
		personDAO.insertPerson(person);
	}

	@Override
	public List<Person> addFindPerson(Person person) {
		personDAO.insertPerson(person);
		return personDAO.selectAllPerson();
	}

	@Override
	public List<Person> updateFindPerson(Person person) {
		personDAO.updatePerson(person);
		return personDAO.selectAllPerson();
	}

	@Override
	public List<Person> findUpdatePerson(Person person) {
		List<Person> list = personDAO.selectAllPerson();
		personDAO.updatePerson(person);
		return list;
	}

}
