package com.example.java11.contollers;

import com.example.java11.entity.Group;
import com.example.java11.repository.GroupRepository;
import com.example.java11.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class GroupControllers {

    @Autowired
    private GroupService groupService;

    @GetMapping("/get_group_by_name")
    @ResponseBody
    public Group getGroupByGroupName(@RequestParam String groupName){
        return groupService.getGroupByGroupName(groupName);
    }

    @GetMapping("/get_group_by_id")
    @ResponseBody
    public Group getGroupById(@RequestParam Long id){
        return groupService.getGroupById(id);
    }


    @GetMapping("/delete_group_by_name")
    @ResponseBody
    public String deleteGroupByGroupName(@RequestParam String groupName){
        groupService.deleteGroupByGroupName(groupName);
        return "Success";
    }

    @GetMapping("/delete_group_by_id")
    @ResponseBody
    public String deleteGroupById(@RequestParam Long id){
        groupService.deleteGroupById(id);
        return "Success";
    }

    @PostMapping("/post_group")
    @ResponseBody
    public String postStudent(@RequestBody Group group){
        groupService.addGroup(group);
        return "Success";
    }


    @GetMapping("/get_all_groups")
    @ResponseBody
    public List<Group> getAllStudent(){
        return groupService.getAllGroups();
    }
}
