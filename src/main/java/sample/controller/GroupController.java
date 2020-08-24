package sample.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import sample.service.GroupService;
import sample.vo.Group;

@Controller
@RequestMapping("/group")
public class GroupController {
	@SuppressWarnings("unused")
	private Logger LOGGER = LoggerFactory.getLogger(GroupController.class);
	
	@Autowired
	GroupService groupService;
	
	@RequestMapping(value="/list", method=GET)
	public String showAllCustomers(Model model) {
		List<Group> groups = groupService.findAll();
		
//		for (Group group : groups) {
//			for (Member member : group.getMembers()) {
//				LOGGER.info(member.toString());
//			}
//		}
		
		model.addAttribute("groups", groups);
		
		return "group/list";
	}
	
	
	@RequestMapping(value="/register", method=POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String registerGroup(@RequestBody Group group) {
		groupService.addGroup(group);
		return "SUCCESS";
	}
	
	@RequestMapping(value="/remove", method=POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String removeGroup(@RequestBody Group group) {
		groupService.removeGroup(group);
		return "SUCCESS";
	}
}
