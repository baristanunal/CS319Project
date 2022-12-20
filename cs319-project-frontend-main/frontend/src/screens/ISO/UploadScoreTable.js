import React, { useEffect } from 'react'
import SelectLabels from '../../components/SelectLabels'
import StyledTable from '../../components/StyledTable'
import Button from '@mui/material/Button';
import UploadIcon from '@mui/icons-material/Upload';
import DownloadIcon from '@mui/icons-material/Download';
import DoneIcon from '@mui/icons-material/Done';
import { useState } from 'react'
import { useGetDataFromExcelMutation } from '../../redux/api/apiSlice';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import axios from 'axios'
import { useUploadFileMutation } from '../../redux/api/apiSlice';

const width = 800;

export default function UploadScoreTable() {
  const [uploadFile] = useUploadFileMutation()
  const [uploadExcel] = useGetDataFromExcelMutation()
  const [file, setFile] = useState()
  const [fileUploaded, setFileUploaded] = useState(false)
  const [fileName, setFileName] = useState("Upload")
  const [text, setText] = useState('')
  const [selector, setSelector] = useState([
    "CS",
    "EEE",
    "ME",
    "IE",
  ])
  const headers = [["File", "File Format", "Status", "Download"]]
  const [rows, setRows] = useState([])

  useEffect(() => {
    if (fileUploaded) {
      let row = []
      row.push(fileName)
      row.push(".xlsx")
      row.push(<DoneIcon/>)
      row.push(<DownloadButton/>)
      setRows([...rows,row])
    }
  }, [fileUploaded])

  function DownloadButton() {
    const onClick = () => {
      axios({
          url: `http://localhost:8080/downloadFile/${fileName}`, //your url
          method: 'GET',
          responseType: 'blob', // important
      }).then((response) => {
          // create file link in browser's memory
          const href = URL.createObjectURL(response.data);
      
          // create "a" HTML element with href to file & click
          const link = document.createElement('a');
          link.href = href;
          link.setAttribute('download', 'ScoreTable.xlsx'); //or any other extension
          document.body.appendChild(link);
          link.click();
      
          // clean up "a" element & remove ObjectURL
          document.body.removeChild(link);
          URL.revokeObjectURL(href);
      });
    }
    return (
      <Button onClick={onClick}>
        <DownloadIcon/>
      </Button>
    )
  }

  const handleChange = (event) => {
    setText(event.target.value);
  };

  const onFileChange = (event) => {
    console.log(event.target.files[0])
    setFile(event.target.files[0])
    setFileName(event.target.files[0].name)
  }

  const handleSubmitButton = (event) => {
    event.preventDefault()
    const formData = new FormData()
    formData.append("file", file, fileName)
    formData.append("PlacementManagerDao", new Blob([JSON.stringify({
      "academicYear": "2022-2023",
      "applicationType": "ERASMUS",
      "departmentName": "CS",
    })], { type: "application/json" }))
    uploadExcel(formData)
    if(file){
      event.preventDefault()
      const formData = new FormData()
      formData.append("file", file)
      formData.append("fileName", file.name)
      formData.append("fileDownloadUri", `http://localhost:8080/downloadFile/${file.name}`)
      formData.append("fileType", file.type)
      formData.append("size", file.size)
      uploadFile(formData)
      setFileUploaded(true)
    }
  }

  return (
    <div>
      <h1>Score Table</h1>
      <FormControl sx={{ m: 1, width: 240, height: 50 }} size="small">
        <InputLabel id="demo-select-small">Department</InputLabel>
        <Select
          labelId="demo-select-small"
          id="demo-select-small"
          value={text}
          label="Department"
          onChange={handleChange}
        >
          <MenuItem value="">
            <em>None</em>
          </MenuItem>
          {selector.map((row, index) =>
            <MenuItem value={index} key={index}>{row}</MenuItem>
          )}
        </Select>
      </FormControl>
      <h2>Upload Here</h2>
      <div style={{ display: "flex" }}>
        {file ?
          <Button sx={{ display: 'flex', marginLeft: 1, backgroundColor: "#008000" }} edge="start" endIcon={<UploadIcon />} variant="contained" component="label">
            {fileName}
            <input onChange={onFileChange} hidden name="file1" accept=".xls, .xlsx" multiple type="file" />
          </Button>
          :
          <Button sx={{ display: 'flex', marginLeft: 1, backgroundColor: "#201F2B" }} edge="start" endIcon={<UploadIcon />} variant="contained" component="label">
            {fileName}
            <input onChange={onFileChange} hidden name="file1" accept=".xls, .xlsx" multiple type="file" />
          </Button>
        }
        <Button onClick={handleSubmitButton} sx={{ display: 'flex', width: 120, marginLeft: 1, backgroundColor: "#201F2B" }} variant="contained">
          Submit
        </Button>
      </div>
      <StyledTable width={width} headers={headers} rows={rows} />
    </div >
  )
}
