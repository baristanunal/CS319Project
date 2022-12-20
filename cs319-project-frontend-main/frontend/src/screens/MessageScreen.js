import React from 'react';
import { styled } from '@mui/material/styles';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import Divider from '@mui/material/Divider';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Fab from '@mui/material/Fab';
import SendIcon from '@mui/icons-material/Send';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import { useGetUserQuery } from '../redux/api/apiSlice';

const useStyles = styled({
    table: {
        minWidth: 650,
    },
    chatSection: {
        width: '100%',
        height: '80vh'
    },
    headBG: {
        backgroundColor: '#e0e0e0'
    },
    borderRight500: {
        borderRight: '1px solid #e0e0e0'
    },
    messageArea: {
        height: '70vh',
        overflowY: 'auto'
    }
});

export default function Chat() {
    const classes = useStyles()
    const { data, error, isLoading, isFetching, isSuccess } = useGetUserQuery()
    const username = data.name
    const userType = data.role
    const contacts = [
        {
            id: 0,
            name: "Can Alkan",
            userType: "Department Erasmus Coordinator",
        },
        {
            id: 1,
            name: "Aysegul Dundar",
            userType: "Department Erasmus Coordinator",
        },
        {
            id: 2,
            name: "Yelda Irem Ates",
            userType: "Administrative Erasmus Coordinator",
        }
    ]
    return (
        <div>
            <Grid container sx={{ mb: 4 }}>
                <Grid item xs={12} >
                    <Typography variant="h5" className="header-message">Messaging</Typography>
                </Grid>
            </Grid>
            <Grid container component={Paper} className={classes.chatSection}>
                <Grid item xs={3} className={classes.borderRight500}>
                    <List>
                        <ListItem>
                            <ListItemIcon>
                                <AccountCircleIcon />
                            </ListItemIcon>
                            <ListItemText primary={username} />
                            <ListItemText secondary={userType} align="right" />
                        </ListItem>
                    </List>
                    <Divider />
                    <Grid item xs={12} style={{ padding: '10px' }}>
                        <TextField id="outlined-basic-email" label="Search" variant="outlined" fullWidth />
                    </Grid>
                    <Divider />
                    <List>
                        {contacts.map((contact) =>
                            <ListItem key={contact.id}>
                                <ListItemIcon>
                                    <AccountCircleIcon />
                                </ListItemIcon>
                                <ListItemText primary={contact.name} />
                                <ListItemText secondary={contact.userType} align="right" />
                            </ListItem>
                        )}
                    </List>
                </Grid>
                <Grid item xs={9}>
                    <List className={classes.messageArea}>
                        <ListItem key="1">
                            <Grid container>
                                <Grid item xs={12}>
                                    <ListItemText align="right" primary="Hocam can you view my preapproval"></ListItemText>
                                </Grid>
                                <Grid item xs={12}>
                                    <ListItemText align="right" secondary="09:30"></ListItemText>
                                </Grid>
                            </Grid>
                        </ListItem>
                        <ListItem key="2">
                            <Grid container>
                                <Grid item xs={12}>
                                    <ListItemText align="left" primary="Sure"></ListItemText>
                                </Grid>
                                <Grid item xs={12}>
                                    <ListItemText align="left" secondary="09:31"></ListItemText>
                                </Grid>
                            </Grid>
                        </ListItem>
                        <ListItem key="3">
                            <Grid container>
                                <Grid item xs={12}>
                                    <ListItemText align="right" primary="Thanks hocam"></ListItemText>
                                </Grid>
                                <Grid item xs={12}>
                                    <ListItemText align="right" secondary="10:30"></ListItemText>
                                </Grid>
                            </Grid>
                        </ListItem>
                    </List>
                    <Divider />
                    <Grid container style={{ padding: '20px' }}>
                        <Grid item xs={11}>
                            <TextField id="outlined-basic-email" label="Type Something" fullWidth />
                        </Grid>
                        <Grid xs={1} align="right">
                            <Fab sx={{ color: "#201F2B" }} aria-label="add"><SendIcon /></Fab>
                        </Grid>
                    </Grid>
                </Grid>
            </Grid>
        </div>
    );
}
