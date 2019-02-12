package kr.co.acomp.hello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.acomp.hello.dao.ArticleDAO;
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
		// TODO Auto-generated method stub
		return null;
	}

	public Article viewArticleDetail(String articleId) {
		return articleDAO.selectArticleById(articleId);
	}
}
