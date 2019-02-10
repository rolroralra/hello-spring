package kr.co.acomp.hello.vo;

import lombok.Getter;
import lombok.Setter;


@Getter 
@Setter
public class Article {
	private int articleId;
	private String author;
	private String title;
	private String content;
	
	
	public Article() {
		super();
	}

	public Article(int articleId, String author, String title, String content) {
		super();
		this.articleId = articleId;
		this.author = author;
		this.title = title;
		this.content = content;
	}
	
	

	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", author=" + author + ", title=" + title + ", content=" + content
				+ "]";
	}
	
}
