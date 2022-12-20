import * as React from 'react';
import StyledTable from '../components/StyledTable';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import AddIcon from '@mui/icons-material/Add';

export default function WishListAddPrevAccepted() {
    function SelectButton() {
        const onClick = () =>
        {

        }
        return (
            <Button onClick={onClick}>
                <AddIcon sx={{color: "#000"}}/>
            </Button>
        )
    }
    const headers = [
        ["Course Code", "Course Name", "ECTS", "Select"]
    ]
    const rows = [
        ["EEE391","Signals", 5, <SelectButton/>],
        ["EEE391","Signals", 5, <SelectButton/>],
        ["EEE391","Signals", 5, <SelectButton/>],
    ]
    return (
        <Box sx={{flexGrow: 1}}>
            <Box sx={{flexGrow: 1}}>
                <h2>Host University Course Transferred</h2>
            </Box>
            <Grid container>
                <Grid xs={12} item>
                    <StyledTable headers={headers} rows={rows}/>
                </Grid>
            </Grid>
        </Box>
    )
    
}