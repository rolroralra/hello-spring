package sample.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import sample.vo.OwnerVO;
import sample.vo.PetVO;
import sample.vo.ProductVO;

public interface ProductDAO {
	ProductVO selectProduct(String name, int price);
	
	void insertOwner(OwnerVO owner);
	
	void insertPet(PetVO pet);
	
	int selectCount(String tableName);
	
	List<Map<String, Object>> selectAllPetOfOwner(String ownerName);
	
	OwnerVO selectOwnerWithPetList(String ownerName);
	
	PetVO selectPetVo(String petName);
	
	Map<String, Object> selectPet(String petName);
	
	Date selectBirthDateOfPet(String petName);
	
	int selectCountOfPet(String ownerName);
	
	int insertOwnerList(List<OwnerVO> ownerList);
	
	int insertPetList(List<PetVO> petList);
	
}
