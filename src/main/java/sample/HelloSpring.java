package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import sample.service.ProductService;

@Component
public class HelloSpring {
	 public static void main(String[] args) {
//		@SuppressWarnings("resource")
//		ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/spring/servlet-context.xml");
//		ProductService productService = ctx.getBean(ProductService.class);
//		System.out.println(ctx.getBean(ProductAspect.class));
//		System.out.println(productService.findProduct());
		
		
		@SuppressWarnings("resource")
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("/spring-bean-config-context.xml");
		ProductService productService2 = beanFactory.getBean(ProductService.class);
		System.out.println(productService2.findProduct());
//		CustomEventListener eventListener = beanFactory.getBean(CustomEventListener.class);
		
		String databaseUrl = "jdbc:mysql://localhost:3306/mysql?characterEncoding=UTF-8&serverTimezone=UTC";
		String databaseUser = "mysql";
		String databasePassword = "mysql";
		Connection conn = null;
		Statement st = null;
		@SuppressWarnings("unused")
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			DriverManager.getDriver(databaseUrl);
			conn = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
			st = conn.createStatement();
			rs = st.executeQuery("SELECT COUNT(*) FROM test_table");
//			pst = conn.prepareStatement("SELECT COUNT(*) FROM test_table");
			
			if (rs.next()) {
				System.out.println("COUNT : " + rs.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
