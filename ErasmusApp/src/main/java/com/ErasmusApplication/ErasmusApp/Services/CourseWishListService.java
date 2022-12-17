package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.*;
import com.ErasmusApplication.ErasmusApp.Repositories.CourseWishListRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service @Transactional
@AllArgsConstructor
public class CourseWishListService {

    CourseWishListRepository courseWishListRepository;


    public CourseWishList saveCourseWishList(CourseWishList courseWishList, Application application){
        courseWishList.setApplication(application);
        return courseWishListRepository.save(courseWishList);
    }


//
//
//    /**
//     * Methods for PreApproval
//     * */
//    public Application addPreApproval(Long appId, PreApproval preApproval){//Works
//        Application app = getApplication(appId);
//        app.setPreApproval(preApproval);
//        return app;
//    }
//    public Form getPreApproval(Long appId){ //Works
//        Application app = getApplication(appId);
//        return app.getPreApproval();
//    }
}
