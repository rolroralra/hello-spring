package sample.service;

import org.springframework.lang.NonNull;

import sample.vo.OwnerVO;
import sample.vo.PetVO;
import sample.vo.ProductVO;

public interface ProductService {
	ProductVO findProduct();
	
	PetVO getPetVO(@NonNull String petName);
	
	void insertPetVO(@NonNull PetVO pet);
	
	void insertOwnerVO(@NonNull OwnerVO owner);
}
