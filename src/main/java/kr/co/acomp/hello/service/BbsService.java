package kr.co.acomp.hello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.acomp.hello.dao.ArticleDAO;
import kr.co.acomp.hello.exception.BizException;
import kr.co.acomp.hello.vo.Article;

@Service
public class BbsService {
	
	@Autowired
	private ArticleDAO articleDAO;
	
//	public void setArticleDAO(ArticleDAO articleDAO) {
//		this.articleDAO = articleDAO;
//	}

	public void registerArticle(Article article) {
		articleDAO.insertArticle(article);
	}

	public Article viewArticle(String articleId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Article> getAllArticles() {
		return null;
	}
	
	public Article getArticleById(String articleId) {
		return articleDAO.selectArticleById(articleId);
	}

	public Article viewArticleDetail(String articleId) {
		return articleDAO.selectArticleById(articleId);
	}
	
	public void testService() {
		System.out.println("target invoked..");
		throw new BizException("testService fail..");
	}

	public List<Article> getArticlesByIds(List<String> idList) {
		return articleDAO.selectArticlesByIds(idList);
	}

	public List<Article> getArticles(Article article) {
		return articleDAO.selectArticles(article);
	}
	
	
}
