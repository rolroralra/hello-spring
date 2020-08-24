package sample.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/spring/spring-bean-config-context.xml")
public class ProductControllerTest {
	
	@Autowired
	ProductController productController;
	
	@Test
	public void test() {
		
	}
}
