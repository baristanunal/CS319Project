import * as React from 'react';
import {useState} from 'react';
import Box from '@mui/material/Box';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import CircleIcon from '@mui/icons-material/Circle';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import Button from '@mui/material/Button';

export default function AnnouncementsScreen() {
    const announcements = ["Announcement 1", "Announcement 2", "Announcement 3"]
    const [openDialog, setOpenDialog] = useState(false)
    const [dialogTitle, setDialogTitle] = useState("")

    const handleClickDialog = (event) => {
        setDialogTitle(event.target.textContent)
        setOpenDialog(true)
    }

    const handleCloseDialog = () => {
        setOpenDialog(false)
    }

    return (
        <Box sx={{flexGrow: 1}}>
            <Box sx={{ flexGrow: 1 }}>
            <h1>Announcements</h1>
            </Box>
            <List>
                {announcements.map((item, index) =>
                <ListItem key={index} disablePadding>
                    <ListItemButton onClick={handleClickDialog}>
                        <ListItemIcon>
                            <CircleIcon/>
                        </ListItemIcon>
                        <ListItemText primary={item}/>
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
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleCloseDialog}>Cancel</Button>
                    <Button onClick={handleCloseDialog}>Subscribe</Button>
                </DialogActions>
            </Dialog>       
        </Box>
    )
}