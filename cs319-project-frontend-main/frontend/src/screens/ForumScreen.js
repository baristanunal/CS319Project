import * as React from 'react';
import { useState } from 'react';
import Box from '@mui/material/Box';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import Button from '@mui/material/Button';
import Divider from '@mui/material/Divider';
import Grid from '@mui/material/Grid';
import { useGetUserQuery } from '../redux/api/apiSlice';

export default function AnnouncementsScreen() {
    const { data, error, isLoading, isFetching, isSuccess } = useGetUserQuery()
    const userType = data.role
    const discussions = [
        {
            id: 0,
            discussionName: "PreApproval Issue",
            openedBy: "Ugur Can Altun"
        },
        {
            id: 1,
            discussionName: "Learning Agreement Issue",
            openedBy: "Kaan Berk Kadabayi"
        },
        {
            id: 2,
            discussionName: "Course Transfer Issue",
            openedBy: "Yusuf Senyuz"
        }
    ]
    const [openDialog, setOpenDialog] = useState(false)
    const [dialogTitle, setDialogTitle] = useState("")

    const handleClickDialog = (event) => {
        setDialogTitle(event.target.textContent)
        setOpenDialog(true)
    }

    const handleCloseDialog = () => {
        setOpenDialog(false)
    }

    const handleOpenDiscussion = () => {
        setDialogTitle("Open Discussion")
        setOpenDialog(true)
    }

    return (
        <Box sx={{ flexGrow: 1 }}>
            <Box sx={{ flexGrow: 1 }}>
                <h1>Discussion Forum</h1>
            </Box>
            <List>
                {discussions.map((discussion) =>
                    <>
                        <ListItem key={discussion.id} disablePadding>
                            <ListItemButton onClick={handleClickDialog}>
                                <ListItemIcon>
                                    <AccountCircleIcon />
                                </ListItemIcon>
                                <ListItemText primary={discussion.discussionName} secondary={discussion.openedBy} />
                            </ListItemButton>
                        </ListItem>
                        <Divider />
                    </>
                )}
            </List>
            {userType === "student" ?
                (<Grid container sx={{ mt: 12 }}>
                    <Grid item>
                        <Button sx={{ backgroundColor: "#201F2B" }} variant="contained" onClick={handleOpenDiscussion}>
                            Open Discussion
                        </Button>
                    </Grid>
                </Grid>) :
                <div />}
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
        </Box>
    )
}