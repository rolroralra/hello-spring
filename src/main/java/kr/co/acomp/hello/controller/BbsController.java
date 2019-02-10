package kr.co.acomp.hello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.acomp.hello.service.BbsService;
import kr.co.acomp.hello.vo.Article;

@Controller
@RequestMapping("/bbs")
public class BbsController {
	@Autowired
	private BbsService bbsService;
	
	@RequestMapping("/write")
	public String write(@RequestParam("author") String author) {
		bbsService.registerArticle(new Article());
		
		System.out.println(author);
		
		return "write_ok";
	}
	
	@RequestMapping("/view/{articeId}")
	public ModelAndView viewDetail(@PathVariable String articleId) {
		Article article = bbsService.viewArticle(articleId);
		return new ModelAndView("bbs/view_detail").addObject("article", article);
	}
	
	@RequestMapping("/list")
	public ModelAndView list() {
		List<Article> articleList = bbsService.getAllArticles();
		
		return new ModelAndView("bbs/list").addObject("articleList", articleList);
	}
	
	@RequestMapping("/header/createauth")
	public String createAuth() {
		return "redirect:main";
//		return "redirect:http://localhost:8080/TotalTest";
//		return "forward:main";
	}
	
	
	@RequestMapping("/test8")
	public ModelAndView test8(@RequestParam(value="id", required=false, defaultValue="") String id) {
		return new ModelAndView("test/test8").addObject("userId", id);
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public ModelAndView test9(
			@RequestParam("email") String email, 
			@RequestParam("name") String name, 
			@RequestParam("password") String password
		) {
		
		return new ModelAndView("bbs/join").addObject("email", email)
										   .addObject("name", name)
										   .addObject("password", password);
	}
	
}
