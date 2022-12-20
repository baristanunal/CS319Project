import * as React from 'react';
import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import MuiAppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';
import List from '@mui/material/List';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import ListItem from '@mui/material/ListItem';
import ForumIcon from '@mui/icons-material/Forum';
import MessageIcon from '@mui/icons-material/Message';
import NotificationsIcon from '@mui/icons-material/Notifications';
import { Link } from 'react-router-dom';
import AccountMenu from './AccountMenu';
import Switch from './Switch'

const drawerWidth = 240;

const AppBar = styled(MuiAppBar, {
    shouldForwardProp: (prop) => prop !== 'open',
})(({ theme, open }) => ({
    zIndex: theme.zIndex.drawer + 1,
    transition: theme.transitions.create(['width', 'margin'], {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
    }),
    ...(open && {
        marginLeft: drawerWidth,
        width: `calc(100% - ${drawerWidth}px)`,
        transition: theme.transitions.create(['width', 'margin'], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.enteringScreen,
        }),
    }),
}));

export default function AppNavbar(props) {
    const userType = localStorage.getItem("role")
    let navBarItems;

    if (userType === "student") {
        navBarItems = [
            {
                id: 0,
                button: (<Button size="large" color="inherit" sx={{ textTransform: 'none' }}> Application </Button>),
                link: "application",
            },
            {
                id: 1,
                button: (<Button size="large" color="inherit" sx={{ textTransform: 'none', textDecoration: 'none' }}> Contacts </Button>),
                link: "contacts",
            },
            {
                id: 2,
                button: (<Button size="large" color="inherit" sx={{ textTransform: 'none' }}> Announcements </Button>),
                link: "announcements",
            },
            {
                id: 3,
                button: (<Button size="large" color="inherit" sx={{ textTransform: 'none' }}> Evaluations </Button>),
                link: "evaluations",
            },
        ]
    }
    else if (userType === "courseCoordinator" || userType === "iso" ||
        userType === "admCoordinator" || userType === "dChair" ||
        userType === "faCommittee" || userType === "dean") {
        navBarItems = [
            {
                id: 1,
                button: (<Button size="large" color="inherit" sx={{ textTransform: 'none', textDecoration: 'none' }}> Contacts </Button>),
                link: "contacts",
            },
            {
                id: 2,
                button: (<Button size="large" color="inherit" sx={{ textTransform: 'none' }}> Announcements </Button>),
                link: "announcements",
            },
            {
                id: 3,
                button: (<Button size="large" color="inherit" sx={{ textTransform: 'none' }}> Evaluations </Button>),
                link: "evaluations",
            }
        ]
    }
    else if (userType === "depCoordinator") {
        navBarItems = [
            {
                id: 1,
                button: (<Button size="large" color="inherit" sx={{ textTransform: 'none', textDecoration: 'none' }}> Contacts </Button>),
                link: "contacts",
            },
            {
                id: 1,
                button: (<Button size="large" color="inherit" sx={{ textTransform: 'none', textDecoration: 'none' }}> Coordinators </Button>),
                link: "coordinators",
            },
            {
                id: 2,
                button: (<Button size="large" color="inherit" sx={{ textTransform: 'none' }}> Announcements </Button>),
                link: "announcements",
            },
            {
                id: 3,
                button: (<Button size="large" color="inherit" sx={{ textTransform: 'none' }}> Evaluations </Button>),
                link: "evaluations",
            }
        ]
    }

    const handleDrawerOpen = () => {
        props.setOpen(true);
    };

    return (
        <AppBar position="fixed" open={props.open} sx={{ backgroundColor: '#201F2B' }}>
            <Toolbar>
                <IconButton
                    aria-label="open drawer"
                    onClick={handleDrawerOpen}
                    size="large"
                    edge="start"
                    color="inherit"
                    sx={{
                        color: "#646C9A",
                        marginRight: 5,
                        ...(props.open && { display: 'none' }),
                    }}
                >
                    <MenuIcon />
                </IconButton>
                <Box>
                    <List sx={{ display: 'flex', flexDirection: 'row' }}>
                        {navBarItems.map((item) => (
                            <Link style={{ color: '#C7C5D8', textDecoration: 'none' }} key={item.id} to={item.link}>
                                <ListItem key={item.id} disablePadding sx={{ display: 'block' }}>
                                    {item.button}
                                </ListItem>
                            </Link>
                        ))}
                    </List>
                </Box>
                <Box sx={{ flexGrow: 1 }} />
                <Switch />
                <Box>
                    <Link style={{ color: '#646C9A' }} to="forum">
                        <IconButton
                            size="large"
                            color="inherit"
                        >
                            <ForumIcon />
                        </IconButton>
                    </Link>
                    <Link style={{ color: '#646C9A' }} to="message">
                        <IconButton
                            size="large"
                            color="inherit"
                        >
                            <MessageIcon />
                        </IconButton>
                    </Link>
                    <IconButton
                        size="large"
                        color="inherit"
                        sx={{ color: '#646C9A' }}
                    >
                        <NotificationsIcon />
                    </IconButton>
                    <IconButton
                        size="large"
                        color="inherit"
                        sx={{ color: '#646C9A' }}
                    >
                        <AccountMenu />
                    </IconButton>
                </Box>
            </Toolbar>
        </AppBar>
    );
}