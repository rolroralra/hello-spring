package sample.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetVO {
	private String petName;
	private String ownerName;
	private int price;
	private Date birthDate;
	private OwnerVO owner;
	
}
