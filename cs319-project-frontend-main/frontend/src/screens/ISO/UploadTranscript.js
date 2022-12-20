import React, { useState } from 'react'
import Button from '@mui/material/Button';
import StyledTable from '../../components/StyledTable';
import Box from '@mui/material/Box';
import Dialog from '@mui/material/Dialog';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import TextField from '@mui/material/TextField';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import { useAddTranscriptMutation } from '../../redux/api/apiSlice';


export default function UploadTranscript() {
  const [openDialog, setOpenDialog] = useState(false)
  const [rows, setRows] = useState([])
  const [university, setUniversity] = useState("")
  const [semester, setSemester] = useState("")
  const [student, setStudent] = useState("")
  const [addTranscript] = useAddTranscriptMutation()

  const handleCloseDialog = () => {
    setOpenDialog(false)
  }

  const handleAddCourse = (event) => {
    event.preventDefault();
    const formData = new FormData(event.currentTarget);
    let row = []
    row.push(formData.get("courseCode"))
    row.push(formData.get("courseName"))
    row.push(formData.get("grade"))
    row.push(formData.get("credits"))
    setRows([...rows, row])
    setOpenDialog(false)
  }

  const handleUniversityLabel = (event) => {
    setUniversity(event.target.value)
  }

  const handleSemesterLabel = (event) => {
    setSemester(event.target.value)
  }

  const handleStudentLabel = (event) => {
    setStudent(event.target.value)
  }

  const handleSubmit = () => {
    const courses = []
    for (let i = 0; i < rows.length; i++) {
      courses.push(
        {
          courseCode: rows[i][0],
          courseName: rows[i][1],
          grade: rows[i][2],
          credits: rows[i][3]
        }
      )
    }
    const transcriptNew = {
      university: university,
      semester: semester,
      student: student,
      transcript: courses
    }
    addTranscript(transcriptNew)
  }

  const universitySelectors = ["AGH", "Vrije", "EPFL"]

  const semesterSelector = ["2022-2023 Fall", "2021-2022 Spring", "2021-2022 Fall"]

  const studentsSelector = ["Ahmet Şahin", "Kaan Berk Kabadayi", "Baris Unal"]

  const headers = [
    ["Course Code", "Course Name", "Grade", "Credits"]
  ];

  return (
    <div>
      <h1>Transcripts</h1>
      <div style={{ display: "flex", justifyContent: "space-between", }}>
        <div>
          <h2>Select University</h2>
          <FormControl sx={{ m: 1, width: 240, height: 50 }} size="small">
            <InputLabel id="demo-select-small">University</InputLabel>
            <Select
              labelId="demo-select-small"
              id="demo-select-small"
              value={university}
              label="University"
              onChange={handleUniversityLabel}
            >
              <MenuItem value="">
                <em>None</em>
              </MenuItem>
              {universitySelectors.map((row, index) =>
                <MenuItem value={row} key={index}>{row}</MenuItem>
              )}
            </Select>
          </FormControl>
        </div>
        <div>
          <h2>Select Semester</h2>
          <FormControl sx={{ m: 1, width: 240, height: 50 }} size="small">
            <InputLabel id="demo-select-small">Semester</InputLabel>
            <Select
              labelId="demo-select-small"
              id="demo-select-small"
              value={semester}
              label="Semester"
              onChange={handleSemesterLabel}
            >
              <MenuItem value="">
                <em>None</em>
              </MenuItem>
              {semesterSelector.map((row, index) =>
                <MenuItem value={row} key={index}>{row}</MenuItem>
              )}
            </Select>
          </FormControl>
        </div>
        <div style={{ marginRight: 30 }}>
          <h2>Select Student</h2>
          <FormControl sx={{ m: 1, width: 240, height: 50 }} size="small">
            <InputLabel id="demo-select-small">Student</InputLabel>
            <Select
              labelId="demo-select-small"
              id="demo-select-small"
              value={student}
              label="Student"
              onChange={handleStudentLabel}
            >
              <MenuItem value="">
                <em>None</em>
              </MenuItem>
              {studentsSelector.map((row, index) =>
                <MenuItem value={row} key={index}>{row}</MenuItem>
              )}
            </Select>
          </FormControl>
        </div>
      </div>
      <div style={{ display: "flex", justifyContent: "center" }}>
        <StyledTable headers={headers} rows={rows} width={801} />
      </div>
      <div style={{ display: "block" }}>
        <div style={{ marginTop: 30, display: "flex", justifyContent: "right" }}>
          <Button sx={{ backgroundColor: "#201F2B" }} onClick={() => setOpenDialog(true)} variant="contained">Add New Course</Button>
        </div>
        <div style={{ marginTop: 10, display: "flex", justifyContent: "right" }}>
          <Button variant="contained" sx={{ backgroundColor: "#201F2B" }} type="submit" onClick={handleSubmit}>Submit</Button>
        </div>
      </div>
      <Dialog open={openDialog} onClose={handleCloseDialog}>
        <DialogTitle>Add New Course</DialogTitle>
        <DialogContent>
          <Box component="form" onSubmit={handleAddCourse} noValidate sx={{ mt: 1 }}>
            <TextField
              margin="normal"
              required
              fullWidth
              id="courseCode"
              label="Course Code"
              name="courseCode"
              autoComplete="courseCode"
              autoFocus
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="courseName"
              label="Course Name"
              id="courseName"
              autoComplete="course-name"
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="grade"
              label="Grade"
              id="grade"
              autoComplete="grade"
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="credits"
              label="Credits"
              id="credits"
              autoComplete="credits"
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ backgroundColor: "#201F2B", mt: 3, mb: 2 }}
            >
              Add Course
            </Button>
          </Box>
        </DialogContent>
      </Dialog>
    </div>
  )
}
