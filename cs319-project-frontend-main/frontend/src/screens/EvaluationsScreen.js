import * as React from 'react';
import { useState } from 'react';
import Box from '@mui/material/Box';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import SelectLabels from '../components/SelectLabels';
import CircleIcon from '@mui/icons-material/Circle';
import Grid from '@mui/material/Grid';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';

export default function EvaluationsScreen() {
    const [openDialog, setOpenDialog] = useState(false)
    const [isMobilityFinished, setIsMobilityFinished] = useState(true)
    const [dialogTitle, setDialogTitle] = useState("")
    const [isAddEval, setIsEval] = useState(false)
    const semesters = ["2022-2023 Fall", "2021-2022 Spring", "2021-2022 Fall", "2020-2021 Spring"]
    const selectors = ["AGH", "Saarland", "Vrije"]

    const handleClickAddEval = () => {
        setDialogTitle("Add Evaluation")
        setIsEval(true)
        setOpenDialog(true)
    }

    const handleClickSemester = (event) => {
        setDialogTitle(event.target.textContent + " Evaluations")
        setOpenDialog(true)
    }

    const handleCloseDialog = () => {
        setIsEval(false)
        setOpenDialog(false)
    }
    return (
        <Box sx={{ flexGrow: 1 }}>
            <Box sx={{ flexGrow: 1 }}>
                <h1>Evaluations</h1>
            </Box>
            <Box sx={{ flexGrow: 1 }}>
                <h2>Select University</h2>
            </Box>
            <Box sx={{ flexGrow: 1 }}>
                <SelectLabels label="Select University" selector={selectors} />
            </Box>
            <List>
                {semesters.map((item, index) =>
                    <ListItem disablePadding key={index}>
                        <ListItemButton onClick={handleClickSemester}>
                            <ListItemIcon>
                                <CircleIcon />
                            </ListItemIcon>
                            <ListItemText primary={item} />
                        </ListItemButton>
                    </ListItem>
                )}
            </List>
            <Dialog open={openDialog} onClose={handleCloseDialog}>
                <DialogTitle>{dialogTitle}</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        To subscribe to this website, please enter your email address here. We
                        will send updates occasionally.
                    </DialogContentText>
                    <TextField
                        autoFocus
                        margin="dense"
                        id="name"
                        label="Email Address"
                        type="email"
                        fullWidth
                        variant="standard"
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleCloseDialog}>Cancel</Button>
                    <Button onClick={handleCloseDialog}>Subscribe</Button>
                </DialogActions>
            </Dialog>
            {isMobilityFinished === true ?
                (<Grid container sx={{ mt: 12 }}>
                    <Grid item>
                        <Button sx={{ backgroundColor: "#201F2B" }} variant="contained" onClick={handleClickAddEval}>
                            Add Evaluation
                        </Button>
                    </Grid>
                </Grid>) :
                <div />}
        </Box>
    )
}