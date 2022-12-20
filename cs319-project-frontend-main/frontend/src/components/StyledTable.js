import * as React from 'react';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: "#201F2B",
    color: theme.palette.common.white,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
  },
}));


export default function CustomizedTables(props) {
  return (
    <TableContainer sx={{ width: props.width, marginTop: 5, marginLeft: 1, }} component={Paper}>
      <Table aria-label="customized table">
        <TableHead>
          <TableRow>
            {props.headers.map((rows) =>
              rows.map((row, index) =>
                <StyledTableCell key={index} align="center">{row}</StyledTableCell>
              )
            )}
          </TableRow>
        </TableHead>

        <TableBody>
          {props.rows.map((row, index) =>
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
  );
}
