package sample.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.customer.biz.domain.Customer;
import com.customer.dao.mapper.CustomerDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		value={
			"file:src/main/webapp/WEB-INF/spring/servlet-context.xml",
			"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		}
)
public class CustomerDAOTest {
	@Autowired
	CustomerDAO customerDAO;
	
	@Test
	public void test() {
		try {
			Customer lsj = customerDAO.selectCustomer(1002);
			lsj.setEmailAddress("lsj@naver.com");
			customerDAO.updateCustomer(lsj);
			
			customerDAO.insertCustomer(lsj);
			for (Customer customer :customerDAO.selectAllCustomer()) {
				System.out.println(customer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
