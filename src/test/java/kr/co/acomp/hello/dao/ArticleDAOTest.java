package kr.co.acomp.hello.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.acomp.hello.vo.Article;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/servlet-context.xml")
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ArticleDAOTest {
	
//	private ApplicationContext ctx;
	
	@Autowired
	private ArticleDAO articleDAO;
	
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("BeforeClass...");
	}
	
	@Before
	public void before() {
		System.out.println("Before...");
//		ctx = new FileSystemXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/*.xml");
	}
	
	@Before
	public void before2() {
		System.out.println("Before2...");
//		ctx = new FileSystemXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/*.xml");
	}
	
	@After
	public void after() {
		System.out.println("After...");
	}
	
	@After
	public void after2() {
		System.out.println("After2...");
	}
	
	@AfterClass
	public static void afterClass() {
		System.out.println("AeforeClass...");
	}
	
	
	/* JUnit Issue */
	// If mvc:resources configuration exists 
	// for Static Web Resource,
	// there will make some Error in ResourceHttpRequestHandler
	@Ignore
	@Test
	public void testSelectArticleById() {
//		ctx = new FileSystemXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/*.xml");
		
//		ArticleDAO articleDAO = ctx.getBean("articleDAO", ArticleDAO.class);
		
//		Article article = articleDAO.selectArticleById(null);
		Article article = articleDAO.selectArticleById(null);
		
		System.out.println(article);
		
		Assert.assertTrue(article.getAuthor().equals("lee"));
		
		
		
	}
	
	@Ignore
	@Test
	public void testInsertArticle() {
		articleDAO.insertArticle(null);
	}
	
	@Test
	public void test() {
		articleDAO.insertArticle(new Article(1001, "shinyoun Kim", "test title 101", "....."));
	}
}
