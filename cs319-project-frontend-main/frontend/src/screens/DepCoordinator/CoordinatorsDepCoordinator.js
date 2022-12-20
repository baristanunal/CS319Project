import * as React from 'react';
import {useState} from 'react';
import Box from '@mui/material/Box';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemText from '@mui/material/ListItemText';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import ListItemIcon from '@mui/material/ListItemIcon';
import CircleIcon from '@mui/icons-material/Circle';
import Divider from '@mui/material/Divider';

export default function CoordinatorsScreen() {
    const [openDialog, setOpenDialog] = useState(false)    
    const coordinators =["Can Alkan", "Aysegul Dundar"]

    const handleOpenDialog = () => {
        setOpenDialog(true)
    }

    const handleCloseDialog = () => {
        setOpenDialog(false)
    }

    return (
        <Box sx={{flexGrow: 1}}>
            <Box sx={{flexGrow: 1}}>
                <h1>Coordinators</h1>
            </Box>
            <Divider/>
            <Box sx={{flexGrow: 1}}>
                <h2>Click on Coordinator to Manage Workload</h2>
            </Box>
            <Divider/>
            <List>
                {coordinators.map((coordinators, index) =>
                <ListItem key={index} disablePadding>
                    <ListItemButton onClick={handleOpenDialog}>
                        <ListItemIcon>
                            <CircleIcon/>
                        </ListItemIcon>
                        <ListItemText primary={coordinators}/>
                    </ListItemButton>
                </ListItem>
                )}
            </List>
            <Dialog open={openDialog} onClose={handleCloseDialog}>
                <DialogTitle>Work Distributon</DialogTitle>
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
        </Box>
    )
}