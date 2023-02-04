package com.ssafy.db.repository;

import com.ssafy.db.entity.Group;
import com.ssafy.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {
    boolean findGroupByName(String name);

    Group findGroupById(int groupId);

    List<Group> findGroupsByOwnerid(User user);
}
