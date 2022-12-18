package com.ErasmusApplication.ErasmusApp.Services;

import com.ErasmusApplication.ErasmusApp.Models.Application;
import com.ErasmusApplication.ErasmusApp.Models.CourseWishList;
import com.ErasmusApplication.ErasmusApp.Models.PreApproval;
import com.ErasmusApplication.ErasmusApp.Repositories.PreApprovalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class PreApprovalService {
    private final PreApprovalRepository preApprovalRepository;

    public PreApproval savePreApprovalToCourseWishList(PreApproval preApproval, CourseWishList courseWishList){
        preApproval.setCourseWishList(courseWishList);
        return preApprovalRepository.save(preApproval);
    }
}
