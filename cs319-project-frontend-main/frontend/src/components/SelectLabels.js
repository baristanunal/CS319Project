import * as React from 'react';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

export default function SelectSmall(props) {
  const [text, setText] = React.useState('')

  const handleChange = (event) => {
    setText(event.target.value);
  };

  return (
    <FormControl sx={{ m: 1, width: 240, height: 50 }} size="small">
      <InputLabel id="demo-select-small">{props.label}</InputLabel>
      <Select
        labelId="demo-select-small"
        id="demo-select-small"
        value={text}
        label={props.label}
        onChange={handleChange}
      >
        <MenuItem value="">
          <em>None</em>
        </MenuItem>
        {props.selector.map((row, index) =>
          <MenuItem value={index} key={index}>{row}</MenuItem>
        )}
      </Select>
    </FormControl>
  );
}
