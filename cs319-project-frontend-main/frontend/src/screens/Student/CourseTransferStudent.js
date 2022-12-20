import * as React from 'react';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import StyledTable from "../../components/StyledTable";
import Button from '@mui/material/Button';
import DownloadIcon from '@mui/icons-material/Download';
import DoneIcon from '@mui/icons-material/Done';

export default function CourseTransfer() {
    const statusHeader = [["Host University", "Status"]];
    const statusRows = [["AGH University", <DoneIcon sx={{ color: "#201F2B" }} />]];
    return (
        <Box sx={{ flexGrow: 1 }}>
            <Box>
                <h1>Course Transfer</h1>
            </Box>
            <Box>
                <h2>Course Transfer Status</h2>
            </Box>
            <StyledTable headers={statusHeader} rows={statusRows} />
        </Box>
    )
} 