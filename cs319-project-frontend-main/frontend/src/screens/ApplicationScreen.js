import * as React from 'react';
import StyledTable from '../components/StyledTable';
import Button from '@mui/material/Button';
import { useGetApplicationQuery, useGetUserQuery } from '../redux/api/apiSlice';
import { useState, useEffect } from 'react'

export default function ApplicationsScreen() {
    const { data, error, isLoading, isFetching, isSuccess } = useGetApplicationQuery()
    const [isErasmus, setIsErasmus] = useState(true)
    const [hasApplication, setHasApplication] = useState(true)
    const [rows, setRows] = useState([])
    const headers = [
        ["#", "Host University", "Applied Academic Semester", "Application Type",
            "Application Status"]
    ] 

    useEffect(() => {
        if(isSuccess){
            console.log(data)
            let row = []
            row.push(1)
            row.push(data.nameOfPlacedHostUniversity)
            row.push(data.appliedAcademicSemester)
            row.push(data.applicationType)
            if(data.placed){
                row.push("Placed")
            }
            else{
                row.push("In Waiting Bin")
            }
            setRows([...rows,row])
        }
    }, [data, isSuccess])


    const width = 1200;

    function ApplicationComp() {

        return (
            <div>
                <h2>Erasmus Program (KA103)</h2>
                <StyledTable width={width} headers={headers} rows={rows} />
                <Button sx={{ backgroundColor: "#751C08", marginTop: 3, marginLeft: 127 }} align="right" variant="contained">Cancel Application</Button>
            </div>
        )
    }

    function InvitationPage() {
        return (
            <div>
                <h2>Erasmus Program (KA103) Invitation</h2>
                <StyledTable width={width} headers={headers} rows={rows} />
                <Button sx={{ backgroundColor: "#035206", marginTop: 3, marginLeft: 107 }} align="right" variant="contained">Accept Invitation</Button>
                <Button sx={{ backgroundColor: "#751C08", marginTop: 3, marginLeft: 1 }} align="right" variant="contained">Reject Invitation</Button>
            </div>
        )
    }

    return (
        hasApplication ? <ApplicationComp /> : <InvitationPage />
    )
}