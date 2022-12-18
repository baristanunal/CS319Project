package com.ErasmusApplication.ErasmusApp.DataOnly;

import lombok.Data;

@Data
public class AddWishDao {
    private boolean approved;
    private String bilkentCourseCode;
    private String bilkentCourseType;
    private double bilkentEcts_credit;

    private String bilkentCourseName;
    private String hostCourseCode;
    private double hostEcts_credit;
    private String hostCourseName;
    private String intent;
    private String standing;
    private String syllabus;

    public AddWishDao(boolean approved, String bilkentCourseCode, String bilkentCourseType, double bilkentEcts_credit, String hostCourseCode, double hostEcts_credit, String hostCourseName, String intent, String standing, String syllabus) {
        this.approved = approved;
        this.bilkentCourseCode = bilkentCourseCode;
        this.bilkentCourseType = bilkentCourseType;
        this.bilkentEcts_credit = bilkentEcts_credit;
        this.hostCourseCode = hostCourseCode;
        this.hostEcts_credit = hostEcts_credit;
        this.hostCourseName = hostCourseName;
        this.intent = intent;
        this.standing = standing;
        this.syllabus = syllabus;
    }

    public AddWishDao() {
    }
}
