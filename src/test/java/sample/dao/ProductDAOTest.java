package sample.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sample.vo.OwnerVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/spring-bean-config-context.xml")
public class ProductDAOTest {
	@Autowired
//	@Qualifier("JDBCProductDAO")
//	@Qualifier("SpringJDBCProductDAO")
	@Qualifier("NPJdbcTemplateProductDAO")
	ProductDAO dao;
	
	
	@Test
	public void test() {
//		productDAO.insertOwner(new OwnerVO("john"));
//		productDAO.insertPet(new PetVO("kitty", "john", 1234, new Date()));
		
//		productDAO.insertPet(new PetVO("doggy", "john", 5000, new Date(System.currentTimeMillis())));
//		productDAO.insertPet(new PetVO("rolroralra", "ksy", 10000, new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 12)));
		
		
//		NPJdbcTemplateProductDAO dao = (NPJdbcTemplateProductDAO)productDAO;
		
//		System.out.println("COUNT = " + productDAO.selectCount("PET"));
//		System.out.println(dao.selectCountOfPet("john"));
//		System.out.println(dao.selectBirthDateOfPet("kitty"));
//		System.out.println(dao.selectPet("kitty"));
//		System.out.println(dao.selectAllPetOfOwner("john"));
//		System.out.println(dao.selectPetVo("rolroralra"));
//		System.out.println(dao.selectOwnerWithPetList("john").toString());
		
//		PetVO pet = new PetVO("tobby", "ksy", 5400, new Date(), new OwnerVO("ksy", null));
//		dao.insertPet(pet);
		
		List<OwnerVO> ownerList = new ArrayList<OwnerVO>();
		ownerList.add(new OwnerVO("admin", null));
		ownerList.add(new OwnerVO("root", null));
		System.out.println("INSERT SUCCESS : " + dao.insertOwnerList(ownerList));
		
//		List<PetVO> petList = new ArrayList<>();
//		petList.add(new PetVO("pet3", "tom misch", 225000, new Date(), null));
//		petList.add(new PetVO("pet4", "json mraz", 10000, new Date(), null));
		
//		System.out.println("INSERT SUCCESS : " + dao.insertPetList(petList));
	}
	
}
