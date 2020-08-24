package sample.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OwnerVO {
	private String ownerName;
	private List<PetVO> petList;
	
	public OwnerVO() {
		petList = new ArrayList<>();
	}
	
	
}
