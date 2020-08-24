package sample.dao;

import java.util.List;

import sample.vo.Group;

public interface GroupDAO {
	
	List<Group> selectAllGroup();
	List<Group> selectGroupByName(String name);
	
	void insertGroup(Group group);
	
	void deleteGroup(Group group);
}
