import * as React from 'react';
import PreApprovalStudent from "./Student/PreApprovalStudent";
import PreApprovalDepCoordinator from "./DepCoordinator/PreApprovalDepCoordinator";
import PreApprovalAdmCoordinator from "./AdmCoordinator/PreApprovalAdmCoordinator";
import PreApprovalFacAdmCoordinator from "./FacAdmCoordinator/PreApprovalFaculty";

export default function PreApprovalScreen() {
    const userType = localStorage.getItem("role")
    return (
        userType === "student"
            ? <PreApprovalStudent /> :
            userType === "depCoordinator"
                ? <PreApprovalDepCoordinator /> :
                userType === "admCoordinator"
                    ? <PreApprovalAdmCoordinator /> :
                    userType === "faCommittee"
                        ? <PreApprovalFacAdmCoordinator /> :
                        <div></div>
    )
}