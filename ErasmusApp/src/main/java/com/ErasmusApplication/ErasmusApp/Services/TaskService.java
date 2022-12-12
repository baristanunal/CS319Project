package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.Student;
import com.ErasmusApplication.ErasmusApp.Models.Task;
import com.ErasmusApplication.ErasmusApp.Models.UserClass;
import com.ErasmusApplication.ErasmusApp.Repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class TaskService {
    TaskRepository taskRepository;

    @Autowired

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task getTask(Long taskId){
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalStateException(
                        "Task With Id: " + taskId + " does not exist."
                ));
    }

    public Task addNewTask(Task task) {
        return taskRepository.save(task);
    }
    public void deleteTask(Long taskId) {
        //TODO add cornercase
        boolean exist = taskRepository.existsById(taskId);
        if(!exist){
            throw new IllegalStateException("Task with Id: " + taskId + " does not exist!");
        }
        taskRepository.deleteById(taskId);
    }

    @Transactional
    public Task updateTask(Long taskId,Task updatedTask){
        try {
            Task task = getTask(taskId);
            task.setContent(updatedTask.getContent());
            task.setDeadline(updatedTask.getDeadline());
            return task;
        }
        catch( IllegalStateException e){
            throw e; //TODO  I do not know how to deal with exceptions
        }
    }
}
