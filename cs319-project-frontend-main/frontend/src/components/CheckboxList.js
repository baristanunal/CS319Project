import * as React from 'react';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemText from '@mui/material/ListItemText';
import Checkbox from '@mui/material/Checkbox';

export default function CheckboxList() {
  const [checked, setChecked] = React.useState([0]);

  const handleToggle = (value) => () => {
    const currentIndex = checked.indexOf(value);
    const newChecked = [...checked];

    if (currentIndex === -1) {
      newChecked.push(value);
    } else {
      newChecked.splice(currentIndex, 1);
    }

    setChecked(newChecked);
  };

  const checklist = [
    "Visit the web page of the host university and learn the application deadline and procedures.",
    "Contact your Departmental Exchange Coordinator; make sure he/she nominated you to the host university.",
    "Participate to the Orientation Program the OISEP organizes.",
    "Apply for a passport or extend it.",
    "Apply to the host university before the application deadline.",
    "Prepare 3 original copies of your Learning Agreement (Latest two months prior to your travel).",
    "Submit one copy of your Learning Agreement to OISEP, send one copy to the host university for their signature, keep one copy with yourself.",
    "After receiving the signed Learning Agreement from the host university submit a copy to OISEP before you leave for Erasmus.",
    "Prepare 3 original copies of your Pre-approval form. (Latest two months prior to your travel).",
    "Submit one copy of your Pre-approval form to OISEP, one to your Departmental Exchange Coordinator and keep one with yourself.",
    "As soon as you receive your Acceptance Letter from the host university submit a copy to OISEP. Latest two months prior to your travel)",
    "Get an Extended Health and Travel Insurance, submit one copy to OISEP. (Latest two months prior to your travel.)",
    "Apply for an Erasmus Student Visa (As soon as you receive your acceptance letter).",
    "Open a EURO bank account at any Yapı Kredi Bank Branch, email the IBAN number and Branch name to OISEP. (Latest two months prior to your travel.)",
    "Sign the Erasmus Grant Agreement with OISEP. (Latest 45 days prior to your travel.)",
    "Take the Online Linguistic Support (OLS) exam 1st step. (After you sign the Erasmus Grant Agreement the exam will be sent to your Bilkent email)"
  ]

  return (
    <>
      <h1>Checklist for Outgoing Students</h1>
      <List sx={{ width: '100%', maxWidth: 1080, bgcolor: 'background.paper' }}>
      {checklist.map((value, i) => {
        const labelId = `checkbox-list-label-${value}`;

        return (
          <ListItem
            key={value}
            disablePadding
          >
            <ListItemButton role={undefined} onClick={handleToggle(value)} dense>
                <Checkbox
                  edge="start"
                  checked={checked.indexOf(value) !== -1}
                  tabIndex={-1}
                  disableRipple
                  inputProps={{ 'aria-labelledby': labelId }}
                />
              <ListItemText id={labelId} primary={checklist[i]} />
            </ListItemButton>
          </ListItem>
        );
      })}
    </List>
    </>
  );
}
