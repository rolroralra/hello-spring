package kr.co.acomp.hello.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.acomp.hello.vo.Article;

@Repository
public class ArticleDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	public void insertArticle(Article article) {
		
		sqlSession.insert(
				"mappers.article-mapper.insertArticle", article);
		System.out.println("insert ok...");
	}

	public Article selectArticleById(String articleId) {
		
		return sqlSession.selectOne("mappers.article-mapper.selectArticleById", articleId);
//		Article article;
//		try {
//			article = new Article(Integer.parseInt(articleId), "lee", "test", "test");
//		} catch (NumberFormatException e) {
//			article = new Article(999, "lee", "test", "test");
//		}
//		return article;
	}
	
	
	public List<Article> selectArticlesByIds(List<String> idList) {
		return sqlSession.selectList("mappers.article-mapper.selectArticlesByIds", idList);
	}
	
	
	public List<Article> selectArticles(Article article) {
		return sqlSession.selectList("mappers.article-mapper.selectArticles", article);
	}
}
