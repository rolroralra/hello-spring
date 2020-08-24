package sample.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import sample.vo.Group;
import sample.vo.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={
		"file:src/main/webapp/WEB-INF/spring/jpa-context.xml"
})
@Transactional
public class GroupDAOTest {
	
	@Autowired
	GroupDAO groupDAO;
	
	@Test
	public void test() {
		try {
//			List<Group> list = groupDAO.selectGroupByName("test");
			List<Group> list = groupDAO.selectAllGroup();
			for (Group group : list) {
				System.out.println(group);
				for (Member member : group.getMembers()) {
					System.out.println(member);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
