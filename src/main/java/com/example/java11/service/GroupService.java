package com.example.java11.service;


import com.example.java11.repository.GroupRepository;
import com.example.java11.entity.Group;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public void addGroup(Group group){
        log.info("Starting the service addGroup");
        groupRepository.saveAndFlush(group);
//        emailService.sendMessage(group.toString());
    }

    @Transactional
    public Group getGroupByGroupName(String groupName) {
        log.info("Starting the service getGroupByGroupName");
        return groupRepository.getGroupByGroupName(groupName);
    }

    @Transactional
    public Group getGroupById(Long id) {
        log.info("Starting the service getGroupById");
        return groupRepository.getById(id);
    }

    @Transactional
    public List<Group> getAllGroups(){
        log.info("Starting the service getAllGroups");
        return groupRepository.findAll();
    }

    @Transactional
    public void deleteGroupById(Long id){
        log.info("Starting the service deleteGroupById");
        groupRepository.deleteById(id);
    }

    @Transactional
    public void deleteGroupByGroupName(String groupName){
        log.info("Starting the service deleteGroupByGroupName");
        groupRepository.deleteGroupByGroupName(groupName);
    }
}
