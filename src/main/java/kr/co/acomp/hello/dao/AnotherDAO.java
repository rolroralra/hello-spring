package kr.co.acomp.hello.dao;

public class AnotherDAO {
	private HelloDAO helloDAO;
	
	public void setHelloDAO(HelloDAO helloDAO) {
		this.helloDAO = helloDAO;
	}

	public int square(int a) {
		return a * a;
	}
	
	public int addTwoNumberAndSquare(int a, int b) {
		return square(helloDAO.addTwoNumber(a, b));
	}
}
