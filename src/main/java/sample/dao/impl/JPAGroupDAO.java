package sample.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import lombok.Setter;
import sample.dao.GroupDAO;
import sample.vo.Group;
import sample.vo.Member;

@Repository
@Setter
public class JPAGroupDAO implements GroupDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Group> selectGroupByName(String name) {
		Query query = entityManager.createQuery("select g FROM Group g where g.name like ?1");
		query.setParameter(1, "%".concat(name).concat("%"));
		@SuppressWarnings("unchecked")
		List<Group> groups = query.getResultList();
//		for (Group group : groups) {
//			for (Member member : group.getMembers()) {
//				member.getId();
//			}
//		}
		
		return groups;
	}
	
	@Override
	public List<Group> selectAllGroup() {
		Query query = entityManager.createQuery("select g FROM Group g");
		@SuppressWarnings("unchecked")
		List<Group> groups = query.getResultList();
		
//		for (Group group : groups) {
//			for (Member member : group.getMembers()) {
//				member.getId();
//			}
//		}
		
		return groups;
	}
	
	@Override
	public void insertGroup(Group group) {
		entityManager.persist(group);
	}
	
	@Override
	public void deleteGroup(Group group) {
		entityManager.remove(group);
	}

	
//	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("manager1");
//	
//	private EntityManager getEntityManager() {
//		return entityManagerFactory.createEntityManager();
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Group> selectGroupByNameByJPA(String name) {
//		EntityManager entityManager = null;
//		EntityTransaction transaction = null;
//		List<Group> groups = null;
//		
//		try {
//			entityManager = getEntityManager();
//			transaction = entityManager.getTransaction();
//			Query query = entityManager.createQuery("SELECT x FROM Group x where x.nave like ?1");
//			query.setParameter(1, "%".concat(name).concat("%"));
//			groups = query.getResultList();
//			transaction.commit();
//			
//			for (Group group : groups) {
//				for (Member member : group.getMembers()) {
//					System.out.println(member.getId());
//				}
//			}
//		} catch (Exception e) {
//			if (entityManager != null && transaction != null) {
//				if (transaction.isActive()) {
//					transaction.rollback();
//				}
//			}
//			
//		} finally {
//			if (entityManager != null) {
//				if (entityManager.isOpen()) {
//					entityManager.close();
//				}
//			}
//		}
//		
//		return groups;
//	}

	
}
