package sample.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

//@NoArgsConstructor

//@Getter
//@Setter
//@ToString
//@RequiredArgsConstructor
@Data
@AllArgsConstructor
public class ProductVO {
	@NonNull
	private String name;
	
	private int price;
	
}
