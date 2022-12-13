package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.Application;
import com.ErasmusApplication.ErasmusApp.Models.Student;
import com.ErasmusApplication.ErasmusApp.Repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationService {
    ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }


    //GET
    public Application getApplication(Long applicationId){
        return applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED,
                                String.format(  "Application With Id: " + applicationId + " does not exist."
                                ))
                );
    }

    public Application getApplicationByStudentAndApplicationId(Long userId, Long applicationId){
        return applicationRepository.findByStudent_IdAndId(userId, applicationId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED,
                                String.format(  "Application With Id: " + applicationId + " does not belong to Student with Id: " + userId
                                ))
                );

    }

    public List<Application> getApplicationsByStudentId(Long userId){
        List list = applicationRepository.findByStudent_Id(userId);
        if(list.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format( "Student with With Id: " + userId + " does not have application"
                    ));
        }
        return list;
    }

    //ADD
    public Application addNewApplication(Student student, Application newApplication){
        newApplication.setStudent(student);
        return applicationRepository.save(newApplication);

    }

    //DELETE
    //TODO Type of boolean or void?
    public boolean removeApplicationOfStudent(Long applicationId, Long userId) {
        //TODO add cornercase
        boolean exist = applicationRepository.existsById(applicationId);
        if(!exist){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format( "Application With Id: " + applicationId + " does not exist in Student with Id:" + userId)
            );

        }
        applicationRepository.deleteById(applicationId);
        return true;
    }

    public void removeApplication(Long applicationId) {
        //TODO add cornercase
        boolean exist = applicationRepository.existsById(applicationId);
        if(!exist){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format( "Application With Id: " + applicationId + " does not exist")
            );
        }
        applicationRepository.deleteById(applicationId);
    }


    //UPDATE
    @Transactional
    public Application updateApplication(Long applicationId,Application updatedApplication){
        try {
            Application application = getApplication(applicationId);
            application.setAll(updatedApplication);
            return application;
        }
        catch( Exception e){
            throw e; //TODO  I do not know how to deal with exceptions
        }

    }

}
