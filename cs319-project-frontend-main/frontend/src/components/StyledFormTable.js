import React from 'react'
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import TextField from '@mui/material/TextField';

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: theme.palette.common.black,
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
          <TableRow sx={{ width: 200 }}>
            {props.headers.map((rows) =>
              rows.map((row, index) =>
                <StyledTableCell key={index} sx={{ width: 200 }} align="left">{row}</StyledTableCell>
              )
            )}
          </TableRow>
        </TableHead>

        <TableBody>
          {props.rows.map((row, index) =>
            <TableRow key={index} sx={{ width: 200 }}>
              <td style={{ display: "flex", width: 200 }}>
                {row.map((index) =>
                  <div key={index} style={{ width: 200 }}>
                    <TextField sx={{ width: 200 }} id="standard-basic" label="Enter" variant="standard" />
                  </div>
                )}
              </td>
            </TableRow>
          )}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
