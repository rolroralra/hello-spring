package kr.co.acomp.hello;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.co.acomp.hello.dao.AnotherDAO;
import kr.co.acomp.hello.dao.HelloDAO;
import kr.co.acomp.hello.service.BbsService;
import kr.co.acomp.hello.service.HelloService;
import kr.co.acomp.hello.vo.Article;

public class HelloMain {
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		AbstractApplicationContext ctx
			= new ClassPathXmlApplicationContext("/spring-context.xml");
		
		
		HelloDAO helloDAO = ctx.getBean("helloDAO", HelloDAO.class);
		AnotherDAO anotherDAO = ctx.getBean("anotherDAO", AnotherDAO.class);
		
		System.out.println(helloDAO.addTwoNumber(5, 7));
		System.out.println(anotherDAO.addTwoNumberAndSquare(5, 7));
		
		
		
		HelloService helloService = ctx.getBean("helloService", HelloService.class);
		
		System.out.println(helloService.addTwoNumberAndSqaure(2, 3));
		

		
		BbsService bbsService = ctx.getBean("bbsService", BbsService.class);
		
		Article article = new Article(101, "Shinoung Kim", "N/A", "N/A");
				
				
		bbsService.registerArticle(article);
		System.out.println(article.getArticleId());
		System.out.println(article.getAuthor());
		System.out.println(article.getTitle());
		System.out.println(article.getContent());
	}
}
