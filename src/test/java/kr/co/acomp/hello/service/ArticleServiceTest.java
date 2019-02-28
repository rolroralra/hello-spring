package kr.co.acomp.hello.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.acomp.hello.vo.Article;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/servlet-context.xml")
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ArticleServiceTest {
	@Autowired
	private BbsService bbsService;
	
	@Test
	public void test() {
//		Article article = bbsService.getArticleById("1001");
//		Assert.assertNotNull(article);
		bbsService.testService();
	}
}
