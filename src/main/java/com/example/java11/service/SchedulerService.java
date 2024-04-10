package com.example.java11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

@Service
public class SchedulerService {

    @Autowired
    GroupService groupService;
    @Autowired
    StudentService studentService;

    @Scheduled(cron = "0 */30 * * * *")
    public void task() throws IOException {
        String path = "C:\\Users\\asus\\IdeaProjects\\java22\\src\\main\\java\\com\\example\\java11\\files";
        System.out.println("Scheduled task");
        for (File myFile : Objects.requireNonNull(new File(path).listFiles())) {
            if (myFile.isFile()) myFile.delete();
        }

        File file = new File(path +"/Students.txt");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(studentService.getAllStudents().toString());
        fileWriter.close();

        file = new File(path +"/Groups.txt");
        file.createNewFile();
        fileWriter = new FileWriter(file);
        fileWriter.write(groupService.getAllGroups().toString());
        fileWriter.close();
    }
}
