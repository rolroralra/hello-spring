package kr.co.acomp.hello.vo;

import java.lang.reflect.Field;

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

	public Article(Article article) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		for (Field field : this.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			field.set(this, field.get(article));
		}
	}

	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", author=" + author + ", title=" + title + ", content=" + content
				+ "]";
	}

}
