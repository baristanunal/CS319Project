import * as React from 'react';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import StyledTable from "../components/StyledTable";
import Button from '@mui/material/Button';
import CancelIcon from '@mui/icons-material/Cancel';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import AvTimerIcon from '@mui/icons-material/AvTimer';
import {
    useGetTasksQuery,
    useUpdateTaskMutation,
    useDeleteTaskMutation
} from '../redux/api/apiSlice';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { useGetUserQuery } from '../redux/api/apiSlice';
import axios from 'axios';

const StyledTableCell = styled(TableCell)(({ theme }) => ({
    [`&.${tableCellClasses.head}`]: {
        backgroundColor: "#201F2B",
        color: theme.palette.common.white,
    },
    [`&.${tableCellClasses.body}`]: {
        fontSize: 14,
    },
}));



function OperationSection(props) {
    const [updateTask] = useUpdateTaskMutation()
    const [deleteTask] = useDeleteTaskMutation()



    const handleCheckButton = () => {
        console.log(props.id)
        updateTask({
            id: props.id,
            content: props.content,
            deadline: props.deadline,
            status: "Completed"
        })
    }

    const handlePendingButton = () => {
        updateTask({
            id: props.id,
            content: props.content,
            deadline: props.deadline,
            status: "Not Completed"
        })
    }

    const handleCancelButton = () => {
        deleteTask(props.id)
    }

    return (
        <Grid container justifyContent="center">
            <Grid item>
                <Button sx={{ color: "#201F2B" }} onClick={handleCheckButton}>
                    <CheckCircleIcon />
                </Button>
            </Grid>
            <Grid item>
                <Button sx={{ color: "#201F2B" }} onClick={handlePendingButton}>
                    <AvTimerIcon />
                </Button>
            </Grid>
            <Grid item>
                <Button sx={{ color: "#201F2B" }} onClick={handleCancelButton}>
                    <CancelIcon />
                </Button>
            </Grid>
        </Grid>
    );
}

export default function ToDoListScreen() {
    const { data, error, isLoading, isFetching, isSuccess } = useGetTasksQuery(localStorage.getItem("token"))
    const { data: userData,  isSuccess:userSuccess } = useGetUserQuery(localStorage.getItem("token"))
    if (userSuccess) {
        console.log(userData)
        localStorage.setItem("fullName", userData.firstName + " " + userData.lastName)
        if (localStorage.getItem("role") == "student") localStorage.setItem("id", userData.schoolId)
    }


    const headers = [
        ["Task", "Deadline", "Status", "Operations"]
    ];

    let rows = [];

    if (isSuccess) {
        for (let i = 0; i < data.length; i++) {
            let props = []
            rows.push([])
            for (const x in data[i]) {
                if (x !== "id") {
                    rows[i].push(data[i][x])
                }
                props.push(data[i][x])
            }
            rows[i].push(<OperationSection content={props[0]} deadline={props[1]} id={props[3]} status={props[2]} />)
        }
    }

    return (
        <Box sx={{ flexGrow: 1 }}>
            <h1>To Do List</h1>
            <Grid container rowSpacing={1} columnSpacing={{ xs: 18 }}>
                <Grid item xs={12}>
                    <TableContainer sx={{ cmarginTop: 5, marginLeft: 1, }} component={Paper}>
                        <Table aria-label="customized table">
                            <TableHead>
                                <TableRow>
                                    {headers.map((rows) =>
                                        rows.map((row, index) =>
                                            <StyledTableCell key={index} align="center">{row}</StyledTableCell>
                                        )
                                    )}
                                </TableRow>
                            </TableHead>

                            <TableBody>
                                {rows.map((row, index) =>
                                    row[2] === "Completed"
                                        ? <TableRow sx={{ backgroundColor: "#5EFF89" }} key={index}>
                                            {row.map((cell, index) => {
                                                return (
                                                    <StyledTableCell key={index} align="center" component="th" scope="row">{cell}</StyledTableCell>
                                                )
                                            })}
                                        </TableRow> :
                                        <TableRow sx={{ backgroundColor: "#ffffe0" }} key={index}>
                                            {row.map((cell, index) => {
                                                return (
                                                    <StyledTableCell key={index} align="center" component="th" scope="row">{cell}</StyledTableCell>
                                                )
                                            })}
                                        </TableRow>
                                )}
                            </TableBody>
                        </Table>
                    </TableContainer>
                </Grid>
            </Grid>
        </Box>
    )
}