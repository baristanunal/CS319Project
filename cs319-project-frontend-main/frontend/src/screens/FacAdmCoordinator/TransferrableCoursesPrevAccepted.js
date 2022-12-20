import * as React from 'react'
import Grid from '@mui/material/Grid'
import DownloadIcon from '@mui/icons-material/Download'
import RemoveCircleIcon from '@mui/icons-material/RemoveCircle'
import Button from '@mui/material/Button';
import StyledTable from "../../components/StyledTable"

export default function TransferrableCoursesPrevAccepted() {
    const headersBilkent = [
        ["Bilkent Course Code", "Bilkent Course Name", "ECTS", "Course Type",
            "Host University Course Name", "Host University Course Name", "ECTS", "Syllabus", "Remove"]
    ]
    const rowsBilkent = [
        ["CS319", "Object Oriented Software Engineering", 5, "Mandatory",
            "CMPE3131", "OOP", 5, <DownloadButton />, <RemoveButton />]
    ]
    function DownloadButton() {
        const onClick = () => {
        }
        return (
            <Button onClick={onClick}>
                <DownloadIcon sx={{ color: "#201F2B" }} />
            </Button>
        )

    }
    function RemoveButton() {
        const onClick = () => {
        }
        return (
            <Button onClick={onClick}>
                <RemoveCircleIcon sx={{ color: "#201F2B" }} />
            </Button>
        )
    }
    return (
        <Grid container columnSpacing={{ xs: 1 }}>
            <StyledTable headers={headersBilkent} rows={rowsBilkent} />
        </Grid>
    )
}