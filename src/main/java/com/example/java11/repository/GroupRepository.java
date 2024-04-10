package com.example.java11.repository;


import com.example.java11.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group getGroupByGroupName(String groupName);


    void deleteGroupByGroupName(String groupName);


}
