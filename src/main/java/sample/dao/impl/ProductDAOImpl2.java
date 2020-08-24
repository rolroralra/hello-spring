package sample.dao.impl;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import sample.dao.ProductDAO;
import sample.vo.OwnerVO;
import sample.vo.PetVO;
import sample.vo.ProductVO;

@Repository("ProductDAOImpl2")
public class ProductDAOImpl2 implements ProductDAO {

	@Override
	public ProductVO selectProduct(String name, int price) {
		return new ProductVO("Impl2::" + name, price);
	}

	@Override
	public void insertOwner(OwnerVO owner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertPet(PetVO pet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int selectCount(String tableName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OwnerVO selectOwnerWithPetList(String ownerName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> selectAllPetOfOwner(String ownerName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PetVO selectPetVo(String petName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectPet(String petName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date selectBirthDateOfPet(String petName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectCountOfPet(String ownerName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertOwnerList(List<OwnerVO> ownerList) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertPetList(List<PetVO> petList) {
		// TODO Auto-generated method stub
		return 0;
	}

}
