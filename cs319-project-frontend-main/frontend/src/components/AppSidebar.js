import * as React from 'react';
import { styled, useTheme } from '@mui/material/styles';
import MuiDrawer from '@mui/material/Drawer';
import List from '@mui/material/List';
import IconButton from '@mui/material/IconButton';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import PlaylistAddCheckIcon from '@mui/icons-material/PlaylistAddCheck';
import SwapHorizIcon from '@mui/icons-material/SwapHoriz';
import ChecklistIcon from '../assets/ChecklistIcon';
import WishListIcon from '../assets/WishlistIcon';
import PreapprovalIcon from '../assets/PreapprovalIcon';
import LearningAgreementIcon from '../assets/LearningAgreementIcon';
import CourseProposalsIcon from '../assets/CourseProposalsIcon';
import DeadlinesIcon from '../assets/DeadlinesIcon';
import PlacementsIcon from '../assets/PlacementsIcon';
import ScoreTableIcon from '../assets/ScoreTableIcon';
import TranscriptsIcon from '../assets/TranscriptsIcon';
import TransferrableCoursesIcon from '../assets/TransferrableCoursesIcon';
import { Link } from 'react-router-dom';

const drawerWidth = 240;

const openedMixin = (theme) => ({
  width: drawerWidth,
  backgroundColor: '#282733',
  transition: theme.transitions.create('width', {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.enteringScreen,
  }),
  overflowX: 'hidden',
});

const closedMixin = (theme) => ({
  transition: theme.transitions.create('width', {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }),
  overflowX: 'hidden',
  backgroundColor: '#282733',
  width: `calc(${theme.spacing(7)} + 1px)`,
  [theme.breakpoints.up('sm')]: {
    width: `calc(${theme.spacing(8)} + 1px)`,
  },
});

const DrawerHeader = styled('div')(({ theme }) => ({
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'flex-end',
  padding: theme.spacing(0, 1),
  // necessary for content to be below app bar
  ...theme.mixins.toolbar,
}));

const Drawer = styled(MuiDrawer, { shouldForwardProp: (prop) => prop !== 'open' })(
  ({ theme, open }) => ({
    width: drawerWidth,
    flexShrink: 0,
    whiteSpace: 'nowrap',
    boxSizing: 'border-box',
    ...(open && {
      ...openedMixin(theme),
      '& .MuiDrawer-paper': openedMixin(theme),
    }),
    ...(!open && {
      ...closedMixin(theme),
      '& .MuiDrawer-paper': closedMixin(theme),
    }),
  }),
);

export default function AppSidebar(props) {
  const userType = localStorage.getItem("role")

  let sideBarItems;

  if (userType === "student") {
    sideBarItems = [
      {
        id: 0,
        label: "To Do List",
        icon: (<PlaylistAddCheckIcon sx={{ color: '#646C9A' }} />),
        link: "todo",
      },
      {
        id: 1,
        label: "Checklist",
        icon: (<ChecklistIcon />),
        link: "checklist",
      },
      {
        id: 2,
        label: "Wish List",
        icon: (<WishListIcon />),
        link: "wishlist",
      },
      {
        id: 3,
        label: "Preapproval",
        icon: (<PreapprovalIcon />),
        link: "preapproval",
      },
      {
        id: 4,
        label: "Learning Agreement",
        icon: (<LearningAgreementIcon />),
        link: "learningAgreement",
      },
      {
        id: 5,
        label: "Course Transfer",
        icon: (<SwapHorizIcon sx={{ color: '#646C9A' }} />),
        link: "courseTransfer",

      }
    ]
  }
  else if (userType === "depCoordinator") {
    sideBarItems = [
      {
        id: 0,
        label: "To Do List",
        icon: (<PlaylistAddCheckIcon sx={{ color: '#646C9A' }} />),
        link: "todo",
      },
      {
        id: 1,
        label: "Placements",
        icon: (<PlacementsIcon />),
        link: "placements",
      },
      {
        id: 2,
        label: "Preapproval",
        icon: (<PreapprovalIcon />),
        link: "preapproval",
      },
      {
        id: 3,
        label: "Course Proposals",
        icon: (<CourseProposalsIcon />),
        link: "courseProposals",
      },
      {
        id: 4,
        label: "Course Transfer",
        icon: (<SwapHorizIcon sx={{ color: '#646C9A' }} />),
        link: "courseTransfer",
      }
    ]
  }
  else if (userType === "courseCoordinator") {
    sideBarItems = [
      {
        id: 0,
        label: "To Do List",
        icon: (<PlaylistAddCheckIcon sx={{ color: '#646C9A' }} />),
        link: "todo",
      },
      {
        id: 1,
        label: "Course Proposals",
        icon: (<CourseProposalsIcon />),
        link: "courseProposals",
      }
    ]
  }
  else if (userType === "iso") {
    sideBarItems = [
      {
        id: 0,
        label: "To Do List",
        icon: (<PlaylistAddCheckIcon sx={{ color: '#646C9A' }} />),
        link: "todo",
      },
      {
        id: 1,
        label: "Placements",
        icon: (<PlacementsIcon />),
        link: "placements",
      },
      {
        id: 2,
        label: "Score Table",
        icon: (<ScoreTableIcon />),
        link: "scoreTable",
      },
      {
        id: 3,
        label: "Transcripts",
        icon: (<TranscriptsIcon />),
        link: "transcripts",
      }
    ]
  }
  else if (userType === "admCoordinator") {
    sideBarItems = [
      {
        id: 0,
        label: "To Do List",
        icon: (<PlaylistAddCheckIcon sx={{ color: '#646C9A' }} />),
        link: "todo",
      },
      {
        id: 1,
        label: "Placements",
        icon: (<PlacementsIcon />),
        link: "placements",
      },
      {
        id: 2,
        label: "Preapproval",
        icon: (<PreapprovalIcon />),
        link: "preapproval",
      },
      {
        id: 3,
        label: "Deadlines",
        icon: (<DeadlinesIcon />),
        link: "deadlines",
      }
    ]
  }
  else if (userType === "dChair" || userType === "dean") {
    sideBarItems = [
      {
        id: 0,
        label: "To Do List",
        icon: (<PlaylistAddCheckIcon sx={{ color: '#646C9A' }} />),
        link: "todo",
      },
      {
        id: 1,
        label: "Course Transfer",
        icon: (<SwapHorizIcon sx={{ color: '#646C9A' }} />),
        link: "courseTransfer",
      }
    ]
  }
  else if (userType === "faCommittee") {
    sideBarItems = [
      {
        id: 0,
        label: "To Do List",
        icon: (<PlaylistAddCheckIcon sx={{ color: '#646C9A' }} />),
        link: "todo",
      },
      {
        id: 1,
        label: "Preapproval",
        icon: (<PreapprovalIcon />),
        link: "preapproval",
      },
      {
        id: 2,
        label: "Transferrable Courses",
        icon: (<TransferrableCoursesIcon />),
        link: "transferrableCourses",
      },
      {
        id: 3,
        label: "Course Transfer",
        icon: (<SwapHorizIcon sx={{ color: '#646C9A' }} />),
        link: "courseTransfer",
      }
    ]
  }

  const theme = useTheme();
  const handleDrawerClose = () => {
    props.setOpen(false);
  };
  return (
    <Drawer variant="permanent" open={props.open}>
      <DrawerHeader>
        <IconButton style={{ color: '#C7C5D8' }} onClick={handleDrawerClose}>
          {theme.direction === 'rtl' ? <ChevronRightIcon /> : <ChevronLeftIcon />}
        </IconButton>
      </DrawerHeader>
      <List sx={{ height: 700, backgroundColor: "#282733" }}>
        {sideBarItems.map((item) => (
          <Link style={{ color: '#C7C5D8', textDecoration: 'none' }} key={item.id} to={item.link}>
            <ListItem key={item.id} disablePadding sx={{ display: 'block' }}>
              <ListItemButton
                sx={{
                  minHeight: 48,
                  justifyContent: props.open ? 'initial' : 'center',
                  px: 2.5,
                }}
              >
                <ListItemIcon
                  sx={{
                    minWidth: 0,
                    mr: props.open ? 3 : 'auto',
                    justifyContent: 'center',
                  }}
                >
                  {item.icon}
                </ListItemIcon>
                <ListItemText primary={item.label} sx={{ opacity: props.open ? 1 : 0 }} />
              </ListItemButton>
            </ListItem>
          </Link>
        ))}
      </List>
    </Drawer>
  );
}