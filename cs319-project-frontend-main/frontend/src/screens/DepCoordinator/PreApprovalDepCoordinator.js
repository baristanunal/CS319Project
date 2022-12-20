import * as React from 'react';
import { useState } from 'react';
import Box from '@mui/material/Box';
import StyledTable from "../../components/StyledTable";
import Button from '@mui/material/Button';
import VisibilityIcon from '@mui/icons-material/Visibility';
import Dialog from '@mui/material/Dialog';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import CloseIcon from '@mui/icons-material/Close';
import Slide from '@mui/material/Slide';
import PreApprovalDepCoordinatorDialog from "./PreApprovalDepCoordinatorDialog";


const Transition = React.forwardRef(function Transition(props, ref) {
    return <Slide direction="down" ref={ref} {...props} />;
});

export default function PreApprovalDepCoordinator() {
    const [openDialog, setOpenDialog] = useState(false)
    const handleCloseDialog = () => {
        setOpenDialog(false)
    }
    function ViewButton() {
        const onClick = () => {
            setOpenDialog(true)
        }
        return (
            <Button style={{ color: "#201F2B" }} onClick={onClick}>
                <VisibilityIcon />
            </Button>
        )
    }
    const headers = [["#", "Name", "Lastname", "Student ID Number", "Preapproval Status", "View"]]
    const rows = [
        [1, "Ugur Can", "Altun", "22002701", "Accepted", <ViewButton />]
    ]

    return (
        <Box sx={{ flexGrow: 1 }}>
            <Box sx={{ flexGrow: 1, mb: -4 }}>
                <h1>Pre Approval</h1>
            </Box>
            <StyledTable headers={headers} rows={rows} />
            <Dialog
                fullScreen
                open={openDialog}
                onClose={handleCloseDialog}
                TransitionComponent={Transition}
            >
                <AppBar sx={{ position: 'relative' }}>
                    <Toolbar sx={{ backgroundColor: "#000" }}>
                        <IconButton
                            edge="start"
                            color="inherit"
                            onClick={handleCloseDialog}
                            aria-label="close"
                        >
                            <CloseIcon />
                        </IconButton>
                        <Typography sx={{ ml: 2, flex: 1 }} variant="h6" component="div">
                            PreApproval Form
                        </Typography>
                        <Button autoFocus color="inherit" onClick={handleCloseDialog}>
                            <CloseIcon />
                        </Button>
                    </Toolbar>
                </AppBar>
                <PreApprovalDepCoordinatorDialog />
            </Dialog>
        </Box>
    )
}