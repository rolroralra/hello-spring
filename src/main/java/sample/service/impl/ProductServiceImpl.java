package sample.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sample.dao.ProductDAO;
import sample.service.ProductService;
import sample.vo.OwnerVO;
import sample.vo.PetVO;
import sample.vo.ProductVO;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
	private Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	// Way1
//	@Autowired								// @Autowired, @Qualifier : spring Annotation
//	@Qualifier("ProductDAOImpl")
	private ProductDAO productDAO;
	
	// Way2
//	@Resource(name="ProductDAOImpl2")		// @Resource : javax Annotation
//	private ProductDAO productDAO;
	
	@PostConstruct
	public void postConstruct() {
		LOGGER.info(this.getClass().getSimpleName() + " BEAN CONSTRUCTED!");
	}
	
	@PreDestroy
	public void preDestory() {
		LOGGER.info(this.getClass().getSimpleName() + " BEAN DESTROYED!");
	}
	
	
	@Override
	public ProductVO findProduct() {
		return productDAO.selectProduct("Product01", 1234);
	}

	@Override
	public PetVO getPetVO(String petName) {
		return productDAO.selectPetVo(petName);
	}

	@Override
	public void insertPetVO(PetVO pet) {
		productDAO.insertPet(pet);
	}

	@Override
	public void insertOwnerVO(OwnerVO owner) {
		productDAO.insertOwner(owner);
	}
	

}
