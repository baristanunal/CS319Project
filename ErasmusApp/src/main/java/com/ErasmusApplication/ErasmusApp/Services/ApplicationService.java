package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.*;
import com.ErasmusApplication.ErasmusApp.Repositories.ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional @AllArgsConstructor
public class ApplicationService {
    ApplicationRepository applicationRepository;
    HostUniversityService hostUniversityService;


    /**
     * Methods for CRUD of Applications
     */
    public Application saveApplication(Student student, Application newApplication){
        newApplication.setStudent(student);
        return applicationRepository.save(newApplication);

    }
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
        if(list == null || list.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format( "Student with With Id: " + userId + " does not have application"
                    ));
        }
        return list;
    }

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

    /**
     * Methods for PreferredUniversities
     * */

    public Application addPreferredUni(Long appId, String nameOfUni){
        Application app = getApplication(appId);
        HostUniversity uni = hostUniversityService.getHostUniByName(nameOfUni);
        app.addPreferredUniversity(uni);
        return  app;
    }
    public List<HostUniversity> getPreferredUniversities(Long appId){
        Application app = getApplication(appId);
        return  app.getPreferredUniversities();
    }

    /**
     * Methods for PlacedHostUni
     * */

    public Application addPlacedUni(Long appId, String nameOfUni){ // works
        Application app = getApplication(appId);
        if( app.getPlacedHostUniversity() != null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format( "Application has aldread placed host uni")
            );
        }
        HostUniversity uni = hostUniversityService.getHostUniByName(nameOfUni);
        app.setPlacedHostUniversity(uni);
//        hostUniversityService.addPlacedApplication(nameOfUni, app);
        return app;
    }
    public HostUniversity getPlacedUni(Long appId){ // works
        Application app = getApplication(appId);
        if( app.getPlacedHostUniversity() == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format( "Application does not have placed uni yet.")
            );
        }
        return app.getPlacedHostUniversity();
    }

    /**
     * Methods for PreApproval
     * */
    public Application addPreApproval(Long appId, PreApproval preApproval){//Works
        Application app = getApplication(appId);
        app.setPreApproval(preApproval);
        return app;
    }
    public Form getPreApproval(Long appId){ //Works
        Application app = getApplication(appId);
        return app.getPreApproval();
    }
}
