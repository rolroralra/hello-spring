package kr.co.acomp.hello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.acomp.hello.service.BbsService;
import kr.co.acomp.hello.vo.Article;

@Controller
@RequestMapping("/bbs")
public class BbsController {
	@Autowired
	private BbsService bbsService;
	
	
//	@GetMapping("/{articleId}")
//	public String viewDetail(@PathVariable String articleId) {
////	public String viewDetail(@PathVariable("articleId") String articleId) {
//		System.out.println("AricleID: " + articleId);
//		return "write_ok";
//	}
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String helloWorld() {
		return "write_ok";
	}
	
//	@RequestMapping(value="/{articleId}", method=RequestMethod.GET)
	@GetMapping("/{articleId}")
	@ResponseBody
	public Article viewDetail(@PathVariable String articleId) {
		System.out.println("viewDetail");
		return bbsService.viewArticleDetail(articleId);
	}
	
//	@RequestMapping("/view/{articeId}")
//	public ModelAndView viewDetail(@PathVariable String articleId) {
//		Article article = bbsService.viewArticle(articleId);
//		return new ModelAndView("bbs/view_detail").addObject("article", article);
//	}
	
	
//	@RequestMapping(value="/write", method=RequestMethod.POST)
//	@PostMapping("/write")
//	public String doWrite(Article article) {
//		bbsService.registerArticle(article);
//		
//		System.out.println("post request...");
//		
//		return "write_ok";
//	}
	
	
	@PostMapping("/write")
	@ResponseBody
	public Article write(@RequestBody Article article) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
//		Article a = new Article(article);
		Article a = article;
		return a;
	}
	
	
	/* x-www-form-urlencoded, form-data */
//	@PostMapping("/write")
//	public ModelAndView doWrite(Article article) {
//		System.out.println("write");
//		bbsService.registerArticle(article);
//		
//		System.out.println("post request");
//		
//		return new ModelAndView("write_ok").addObject("article", article);
//	}
	
	@GetMapping("/write")
	public String write() {
		System.out.println("write");
		bbsService.registerArticle(new Article());
		
		System.out.println("get request...");
		
		return "write_ok";
	}
	

	
	@RequestMapping("/list")
	public ModelAndView list() {
		System.out.println("list");
		List<Article> articleList = bbsService.getAllArticles();
		
		return new ModelAndView("bbs/list").addObject("articleList", articleList);
	}
	
	@RequestMapping("/header/createauth")
	public String createAuth() {
		System.out.println("createAuth");
		return "redirect:main";
//		return "redirect:http://localhost:8080/TotalTest";
//		return "forward:main";
	}
	
	
	@RequestMapping("/test8")
	public ModelAndView test8(@RequestParam(value="id", required=false, defaultValue="") String id) {
		System.out.println("test8");
		return new ModelAndView("test/test8").addObject("userId", id);
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public ModelAndView test9(
			@RequestParam("email") String email, 
			@RequestParam("name") String name, 
			@RequestParam("password") String password
		) {
		System.out.println("test9");
		
		return new ModelAndView("bbs/join").addObject("email", email)
										   .addObject("name", name)
										   .addObject("password", password);
	}
	
}
