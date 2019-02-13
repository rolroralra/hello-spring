package kr.co.acomp.hello.dao;

import org.springframework.stereotype.Repository;

import kr.co.acomp.hello.vo.Article;

@Repository
public class ArticleDAO {
	
	public void insertArticle(Article article) {
		System.out.println(article);
		System.out.println("insert ok...");
	}

	public Article selectArticleById(String articleId) {
		Article article;
		try {
			article = new Article(Integer.parseInt(articleId), "lee", "test", "test");
		} catch (NumberFormatException e) {
			article = new Article(999, "lee", "test", "test");
		}
		return article;
	}
	
}
