package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.service.ProductService;
import sample.vo.OwnerVO;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		
		productService.insertOwnerVO(new OwnerVO("user01", null));
		return "index";
	}
	
	@ResponseBody
	@RequestMapping(value="/findProduct", method=RequestMethod.GET)
	public String findProduct() {
		System.out.println(productService.findProduct());
		
		return "index";
	}
	
	
}
