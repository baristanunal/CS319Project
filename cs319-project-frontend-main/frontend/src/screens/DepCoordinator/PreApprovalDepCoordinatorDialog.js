import * as React from 'react';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import StyledTable from "../../components/StyledTable";
import Button from '@mui/material/Button';

export default function PreApprovalDepCoordinatorDialog() {
    const headersEmpty = [];
    const rowsEmpty = [];
    const rowsName = [["Name", " ", "ID Number", " "],
    ["Surname", " ", "Department", " "]];
    const rowNameOfInstitution = [["Name of the institution", " "]];
    const rowAcademicYear = [["Academic Year", " "],
    ["Semester", " "]];
    const infoHeader = [["Host institution courses to be transferred upon approval",
        "Course or requirement to be exempted if transferred course is completed with a passing grade †"]];
    const headersCourseTransfer = [[" ", " Course Code", "Course Name", "Credits",
        "Course Code and Name for a Required Course, Elective Group Name for an Elective Requirement",
        "Credits", "Elective Requirement Exemptions only: Course code(s) of directly equivalent course(s), if any **"]];
    const rowsCourseTransfer = [[1, " ", " ", " ", " ", " ", " "],
    [2, " ", " ", " ", " ", " ", " "],
    [3, " ", " ", " ", " ", " ", " "],
    [4, " ", " ", " ", " ", " ", " "],
    [5, " ", " ", " ", " ", " ", " "],
    [6, " ", " ", " ", " ", " ", " "]];
    const headerSignatures = [["Approved by", "Name", "Signture", "Date"]];
    const rowsSignatures = [[" ", " ", " ", " "]];
    return (
        <Grid container>
            <Grid item xs={12}>
                <StyledTable headers={headersEmpty} rows={rowsName} />
            </Grid>
            <Grid item xs={6}>
                <StyledTable headers={headersEmpty} rows={rowNameOfInstitution} />
            </Grid>
            <Grid item xs={6}>
                <StyledTable headers={headersEmpty} rows={rowAcademicYear} />
            </Grid>
            <Box sx={{ flexGrow: 1, mb: -4 }}>
                <Grid item xs={12}>
                    <StyledTable headers={infoHeader} rows={rowsEmpty} />
                </Grid>
            </Box>
            <Grid item xs={12}>
                <StyledTable headers={headersCourseTransfer} rows={rowsCourseTransfer} />
            </Grid>
            <Grid item xs={12}>
                <StyledTable headers={headerSignatures} rows={rowsSignatures} />
            </Grid>
            <Grid item xs={8}>
            </Grid>
            <Box sx={{ flexGrow: 1, mt: 4, ml: 24 }}>
                <Grid item xs={2}>
                    <Button sx={{ backgroundColor: "#000" }} variant="contained">
                        Approve
                    </Button>
                </Grid>
            </Box>
            <Box sx={{ flexGrow: 1, mt: 4 }}>
                <Grid item xs={2}>
                    <Button sx={{ backgroundColor: "#000" }} variant="contained">
                        Decline
                    </Button>
                </Grid>
            </Box>
        </Grid>
    )
}