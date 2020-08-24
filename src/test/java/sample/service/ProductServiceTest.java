package sample.service;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/spring-bean-config-context.xml")
public class ProductServiceTest {
	@Autowired
	ProductService productService;
	
	@Autowired
	MessageSource messageSource;
	
	@Test
	public void testFindProduct() {
		
		try {
			System.out.println(messageSource.getMessage("NotEmpty.article.title", null, Locale.getDefault()));
			System.out.println(messageSource.getMessage("NotEmpty.article.name", null, Locale.getDefault()));
		} catch (NoSuchMessageException e) {
			e.printStackTrace();
		}
		
//		try {
//			productService.insertOwnerVO(new OwnerVO("lsj", null));
////			productService.insertPetVO(new PetVO("rolroralra", "john", 7777, new Date(), new OwnerVO("john", null)));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
