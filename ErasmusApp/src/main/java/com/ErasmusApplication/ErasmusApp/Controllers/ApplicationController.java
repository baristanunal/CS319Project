package com.ErasmusApplication.ErasmusApp.Controllers;

import com.ErasmusApplication.ErasmusApp.Models.Student;
import com.ErasmusApplication.ErasmusApp.Services.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Application")
public class ApplicationController {
    ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    //TODO type of return
    @PostMapping("/{userId]/remove/{applicationId}")
    public void removeApplication(@PathVariable Long userId, @PathVariable Long applicationId){
        applicationService.removeApplication(userId,applicationId);
    }


}
