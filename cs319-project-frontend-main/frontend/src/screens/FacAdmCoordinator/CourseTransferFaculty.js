import * as React from 'react';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import StyledTable from "../../components/StyledTable";
import Button from '@mui/material/Button';
import SelectLabels from '../../components/SelectLabels';
import Typography from '@mui/material/Typography';

export default function CourseTransferFaculty() {
    const selectors = [
        "A",
    ]
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
        <Box sx={{ flexGrow: 1 }}>
            <Box>
                <h1>Course Transfer</h1>
            </Box>
            <div style={{ display: "flex", justifyContent: "space-between", }}>
                <div>
                    <h2>Select University</h2>
                    <SelectLabels selector={selectors} label="Select" />
                </div>
                <div>
                    <h2>Select Semester</h2>
                    <SelectLabels selector={selectors} label="Select" />
                </div>
                <div style={{ marginRight: 30 }}>
                    <h2>Select Student</h2>
                    <SelectLabels selector={selectors} label="Select" />
                </div>
            </div>
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
                <Grid item xs={3} sx={{ mt: 4 }}>
                    <Typography variant="body1" gutterBottom>
                        Download Form
                    </Typography>
                    <Button sx={{ backgroundColor: "#201F2B" }} variant="contained">Download</Button>
                </Grid>
                <Grid item xs={3} sx={{ mt: 4 }}>
                    <Typography variant="body1" gutterBottom>
                        Upload Form With Signature
                    </Typography>
                    <Button sx={{ backgroundColor: "#201F2B" }} variant="contained">Upload</Button>
                </Grid>
            </Grid>
        </Box>
    )
}