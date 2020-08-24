package sample.service;

import java.util.List;

import sample.vo.Group;

public interface GroupService {
	List<Group> findAll();

	void addGroup(Group group);

	void removeGroup(Group group);
}
