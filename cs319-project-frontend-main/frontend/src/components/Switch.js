import React, { useState } from "react";
import FormGroup from "@mui/material/FormGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import Switch from "@mui/material/Switch";
import { createTheme, ThemeProvider } from "@mui/material/styles";

export default function CustomizedSwitches() {
    const [state, setState] = React.useState({
        checkedA: true
    });

    const [text, setText] = React.useState({ type: "Erasmus", checked: true });


    const handleChange = (event) => {
        setState({ ...state, [event.target.name]: event.target.checked });
        setText(text.checked ? { type: "Exchange", checked: false } : { type: "Erasmus", checked: true });
    };

    const theme = createTheme({
        components: {
            MuiSwitch: {
                styleOverrides: {
                    switchBase: {
                        // Controls default (unchecked) color for the thumb
                        color: "#646C9A"
                    },
                    colorPrimary: {
                        "&.Mui-checked": {
                            // Controls checked color for the thumb
                            color: "#646C9A"
                        }
                    },
                    track: {
                        // Controls default (unchecked) color for the track
                        opacity: 0.2,
                        backgroundColor: "#646C9A",
                        ".Mui-checked.Mui-checked + &": {
                            // Controls checked color for the track
                            opacity: 0.7,
                            backgroundColor: "#646C9A"
                        }
                    }
                }
            }
        }
    });

    return (
        <ThemeProvider theme={theme}>
            <p style={{ color: "#C7C5D8" }}>{text.type}</p>
            <FormGroup>
                <FormControlLabel
                    sx={{ marginRight: -1 }}
                    control={
                        <Switch
                            sx={{ marginLeft: 1 }}
                            checked={state.checkedA}
                            onChange={handleChange}
                            name="checkedA"
                        />
                    }
                />
            </FormGroup>
        </ThemeProvider>
    );
}
