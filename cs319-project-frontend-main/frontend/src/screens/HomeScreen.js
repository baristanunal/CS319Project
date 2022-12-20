import * as React from 'react';
import Box from '@mui/material/Box';
import { styled } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import AppNavbar from '../components/AppNavbar';
import AppSidebar from '../components/AppSidebar';
import { Outlet } from "react-router-dom";


function SidebarAndNavbar () {
    const [open, setOpen] = React.useState(false);
    return (
      <>
      <AppNavbar open={open} setOpen={setOpen}/>
      <AppSidebar open={open} setOpen={setOpen}/>
      </>
    );
}
  
const DrawerHeader = styled('div')(({ theme }) => ({
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'flex-end',
    padding: theme.spacing(0, 1),
    // necessary for content to be below app bar
    ...theme.mixins.toolbar,
}));

export default function HomeScreen() {
    return (
        <Box sx={{ display: 'flex' }}>
            <CssBaseline />
            <SidebarAndNavbar/>
            <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
                <DrawerHeader />
                <Outlet />
            </Box>
        </Box>
    )
}