import * as React from 'react'
import { useState } from 'react'
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Grid from '@mui/material/Grid'
import StyledFormTable from "../../components/StyledFormTable"

export default function TransferrableCoursesNewCourses() {
    const [rows, setRows] = useState([[0, 1, 2]]);
    const headers = [
        ["Course Code", "Course Name", "ECTS"]
    ]
    return (
        <>
            <Box sx={{ ml: 18 }}>
                <StyledFormTable rows={rows} headers={headers} width={601} />
            </Box>
            <Grid container sx={{ mt: 6 }}>
                <Grid item xs={6}>
                    <Button sx={{ backgroundColor: "#201F2B" }} variant="contained">Upload Syllabus</Button>
                </Grid>
                <Grid item xs={6}>
                    <Button sx={{ backgroundColor: "#201F2B" }} variant="contained">Submit New Course</Button>
                </Grid>
            </Grid>
        </>
    )
}