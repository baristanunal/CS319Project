import * as React from 'react'
import { useState } from 'react'
import Box from '@mui/material/Box'
import Grid from '@mui/material/Grid'
import SelectLabels from "../../components/SelectLabels"
import FilledCircleIcon from '@mui/icons-material/Circle'
import OutlinedCircleIcon from '@mui/icons-material/CircleOutlined'
import Button from '@mui/material/Button'
import Typography from '@mui/material/Typography'
import TransferrableCoursesPrevAccepted from "./TransferrableCoursesPrevAccepted"
import TransferrableCoursesNewCourses from "./TransferrableCoursesNewCourses"

export default function TransferrableCoursesScreen() {
    const [isPreviouslyAcceptedCourses, setIsPreviouslyAcceptedCourses] = useState(true)
    const universitySelectors = ["AGH", "Vrije", "Saarland"]
    const departmentSelectors = ["Computer Science", "Industrial Engineering", "Electrical and Electronics Engineering"]

    const handleClickPreviouslyAccepted = () => {
        setIsPreviouslyAcceptedCourses(true)
    }

    const handleClickNewCourses = () => {
        setIsPreviouslyAcceptedCourses(false)
    }

    return (
        <Box sx={{ flexGrow: 1 }}>
            <Box sx={{ flexGrow: 1 }}>
                <h1>Transferrable Courses</h1>
            </Box>
            <Grid container>
                <Grid item xs={4}>
                    <Button onClick={handleClickPreviouslyAccepted}>
                        {isPreviouslyAcceptedCourses
                            ? <FilledCircleIcon sx={{ color: "#201F2B" }} /> :
                            <OutlinedCircleIcon sx={{ color: "#201F2B" }} />}
                        <Typography sx={{ color: "#201F2B", ml: 1, mt: 1 }} variant="body1" gutterBottom>
                            <strong>Previously Accepted</strong>
                        </Typography>
                    </Button>
                </Grid>
                <Grid item xs={4}>
                    <Button onClick={handleClickNewCourses}>
                        {!isPreviouslyAcceptedCourses
                            ? <FilledCircleIcon sx={{ color: "#201F2B" }} /> :
                            <OutlinedCircleIcon sx={{ color: "#201F2B" }} />}
                        <Typography sx={{ color: "#201F2B", ml: 1, mt: 1 }} variant="body1" gutterBottom>
                            <strong>New Course</strong>
                        </Typography>
                    </Button>
                </Grid>
            </Grid>
            <Grid container>
                <Grid item xs={4}>
                    <SelectLabels selector={universitySelectors} label="Select University" />
                </Grid>
                <Grid item xs={4}>
                    <SelectLabels selector={departmentSelectors} label="Select Department" />
                </Grid>
            </Grid>
            {
                isPreviouslyAcceptedCourses
                    ? <TransferrableCoursesPrevAccepted /> :
                    <TransferrableCoursesNewCourses />
            }
        </Box>
    )
}