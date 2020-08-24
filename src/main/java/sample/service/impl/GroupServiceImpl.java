package sample.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.dao.GroupDAO;
import sample.service.GroupService;
import sample.vo.Group;
import sample.vo.Member;

@Service
public class GroupServiceImpl implements GroupService {
	private Logger LOGGER = LoggerFactory.getLogger(GroupServiceImpl.class);
	
	@Autowired
	GroupDAO groupDAO;

	
	@Override
	public List<Group> findAll() {
		List<Group> groups = groupDAO.selectAllGroup();
		for (Group group : groups) {
			for (Member member : group.getMembers()) {
				LOGGER.info("" + member.getId());
			}
		}
		
		return groups;
	}


	@Override
	public void addGroup(Group group) {
		groupDAO.insertGroup(group);
	}


	@Override
	public void removeGroup(Group group) {
		groupDAO.deleteGroup(group);
	}

}
