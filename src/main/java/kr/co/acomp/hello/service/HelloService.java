package kr.co.acomp.hello.service;

import kr.co.acomp.hello.dao.AnotherDAO;
import kr.co.acomp.hello.dao.HelloDAO;

public class HelloService {
	
	private HelloDAO helloDAO;
	private AnotherDAO anotherDAO;
	
	public HelloService(HelloDAO helloDAO, AnotherDAO anotherDAO) {
		super();
		this.helloDAO = helloDAO;
		this.anotherDAO = anotherDAO;
	}
	
	public int addTwoNumberAndSqaure(int a, int b) {
		return anotherDAO.square(helloDAO.addTwoNumber(a, b));
	}
	
	
	
}
