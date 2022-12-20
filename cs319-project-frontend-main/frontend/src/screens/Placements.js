import React from 'react'
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { useGetPlacementsQuery } from '../redux/api/apiSlice'
import { useState, useEffect } from 'react'
import Button from '@mui/material/Button'

const headers = [
    ["#",
    "First Name",
    "Last Name",
    "Student ID",
    "Total Point",
    "Duration Preferred", 
  "Placed University"]
]

const width = 1200;

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: "#201F2B",
    color: theme.palette.common.white,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
  },
}));


export default function Placements() {
  const { data, error, isLoading, isFetching, isSuccess } = useGetPlacementsQuery()
  const [placedRow, setPlacedRow] = useState([]);
  const [waitingRow, setWaitingRow] = useState([])

  useEffect(() => {
    if (isSuccess) {
      console.log(data)
        let placedRows = []
        for (let i = 0; i < data.placementTable.applications.length; i++) {
            let row = []
            row.push(i+1)
            row.push(data.placementTable.applications[i].firstName)
            row.push(data.placementTable.applications[i].lastName)
            row.push(data.placementTable.applications[i].schoolId)
            row.push(data.placementTable.applications[i].totalPoints)
            row.push(data.placementTable.applications[i].appliedAcademicSemester)
            row.push(data.placementTable.applications[i].nameOfPlacedHostUniversity)
            placedRows.push(row)
        }
        setPlacedRow([...placedRow, ...placedRows])
        let waitingRows = []
        for (let i = 0; i < data.waitingBin.applications.length; i++) {
          let row = []
          row.push(i+1)
          row.push(data.waitingBin.applications[i].firstName)
          row.push(data.waitingBin.applications[i].lastName)
          row.push(data.waitingBin.applications[i].schoolId)
          row.push(data.waitingBin.applications[i].totalPoints)
          row.push(data.waitingBin.applications[i].appliedAcademicSemester)
          row.push(data.waitingBin.applications[i].nameOfPlacedHostUniversity)
          waitingRows.push(row)
        }
        setWaitingRow([...waitingRow, ...waitingRows])

    }
  }, [data, isSuccess])

  return (
    <div>
        <h1>Placements</h1>
        <TableContainer sx={{ marginTop: 5, marginLeft: 1, }} component={Paper}>
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
              {console.log(placedRow)}
              {placedRow.map((row, index) =>
                <TableRow key={index}>
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
        <h1>Waiting Bin</h1>
        <TableContainer sx={{ marginTop: 5, marginLeft: 1, }} component={Paper}>
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
              {waitingRow.map((row, index) =>
                <TableRow key={index}>
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
        <Button variant="contained" sx={{backgroundColor: "#201F2B", mt: 4}}>
          Submit
        </Button>
    </div>
  )
}
