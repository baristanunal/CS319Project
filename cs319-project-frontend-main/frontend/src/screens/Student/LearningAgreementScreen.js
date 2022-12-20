import * as React from 'react';
import Button from '@mui/material/Button';
import CircleIcon from '@mui/icons-material/Circle';
import { Grid } from '@mui/material';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import { useState } from 'react'
import { useUploadFileMutation } from '../../redux/api/apiSlice';
import axios from 'axios'
import UploadIcon from '@mui/icons-material/Upload';
import Divider from '@mui/material/Divider';

export default function LearningAgreementScreen() {
    const [uploadFile] = useUploadFileMutation()
    const [file, setFile] = useState()
    const [fileName, setFileName] = useState("Upload")

    const onFileChange = (event) => {
        setFile(event.target.files[0])
        setFileName(event.target.files[0].name)
    }

    const handleUploadButton = (event) => {
        if(file){
            event.preventDefault()
            const formData = new FormData()
            formData.append("file", file)
            formData.append("fileName", file.name)
            formData.append("fileDownloadUri", `http://localhost:8080/downloadFile/${file.name}`)
            formData.append("fileType", file.type)
            formData.append("size", file.size)
            uploadFile(formData)
        }
    }

    const handleDownloadButton = () => {
        axios({
            url: `http://localhost:8080/downloadFile/${file.name}`, //your url
            method: 'GET',
            responseType: 'blob', // important
        }).then((response) => {
            // create file link in browser's memory
            const href = URL.createObjectURL(response.data);
        
            // create "a" HTML element with href to file & click
            const link = document.createElement('a');
            link.href = href;
            link.setAttribute('download', 'learningAgreement.pdf'); //or any other extension
            document.body.appendChild(link);
            link.click();
        
            // clean up "a" element & remove ObjectURL
            document.body.removeChild(link);
            URL.revokeObjectURL(href);
        });
    }

    return (
        <>
            <h1>Student Learning Agreement</h1>
            <h2>Before the Mobility</h2>
            <Grid container direction="row" alignItems="center" spacing={2} wrap="nowrap">
                <Grid item>
                    <CircleIcon />
                </Grid>
                <Grid item>
                    <Typography variant="body1" gutterBottom>
                        Provide <strong>mobility programme</strong>
                    </Typography>
                </Grid>
            </Grid>
            <Grid container direction="row" alignItems="center" spacing={2} wrap="nowrap">
                <Grid item>
                    <CircleIcon />
                </Grid>
                <Grid item>
                    <Typography variant="body1" gutterBottom>
                        Identify <strong>responsible persons</strong>
                    </Typography>
                </Grid>
            </Grid>
            <Grid container direction="row" alignItems="center" spacing={2} wrap="nowrap">
                <Grid item>
                    <CircleIcon />
                </Grid>
                <Grid item>
                    <Typography variant="body1" gutterBottom>
                        <strong>Commitment</strong> of the three parties with original / scanned/ digital signatures.
                    </Typography>
                </Grid>
            </Grid>
            <h2>During the Mobility</h2>
            <Grid container direction="row" alignItems="center" spacing={2} wrap="nowrap">
                <Grid item>
                    <CircleIcon />
                </Grid>
                <Grid item>
                    <Typography variant="body1" gutterBottom>
                        If modifications <strong>are needed</strong>
                    </Typography>
                </Grid>
            </Grid>
            <Grid container direction="row" alignItems="center" spacing={2} wrap="nowrap">
                <Grid item>
                    <CircleIcon />
                </Grid>
                <Grid item>
                    <Typography variant="body1" gutterBottom>
                        A party requests changes in the first <strong>2 to 5-week</strong> period after the start of regular classes/ educational components
                        (after the start of each semester) <i>N.B.: Request for extension of the duration to be made up to one month before the
                            foreseen end date</i>.
                    </Typography>
                </Grid>
            </Grid>
            <Grid container direction="row" alignItems="center" spacing={2} wrap="nowrap">
                <Grid item>
                    <CircleIcon />
                </Grid>
                <Grid item>
                    <Typography variant="body1" gutterBottom>
                        Agreement by email by the three parties within a <strong>2-week period</strong> after the request.
                        <i>N.B.: Changes to be agreed within 4 to 7 weeks after the start date of the studies</i>.
                    </Typography>
                </Grid>
            </Grid>
            <h2>After the Mobility</h2>
            <Grid container direction="row" alignItems="center" spacing={2} wrap="nowrap">
                <Grid item>
                    <CircleIcon />
                </Grid>
                <Grid item>
                    <Typography variant="body1" gutterBottom>
                        Receiving institution provides <strong>Transcript of Records</strong> to student and sending institution in period stipulated
                        in IIA (normally <strong>max. 5 weeks</strong> after results).
                    </Typography>
                </Grid>
            </Grid>
            <Grid container direction="row" alignItems="center" spacing={2} wrap="nowrap">
                <Grid item>
                    <CircleIcon />
                </Grid>
                <Grid item>
                    <Typography variant="body1" gutterBottom>
                        Sending institution provides the <strong>Transcript of Records</strong> to the student within <strong>5 weeks</strong>.
                    </Typography>
                </Grid>
            </Grid>
            <Box sx={{ m: 3 }} />
            <Grid container direction="row" alignItems="center" spacing={2} wrap="nowrap">
                <Grid item >
                {file ?
                <Button sx={{ display: 'flex', marginLeft: 1, backgroundColor: "#008000" }} edge="start" endIcon={<UploadIcon />} variant="contained" component="label">
                    {fileName}
                    <input onChange={onFileChange} hidden name="file1" accept="application/pdf" multiple type="file" />
                </Button>
                :
                <Button sx={{ display: 'flex', marginLeft: 1, backgroundColor: "#201F2B" }} edge="start" endIcon={<UploadIcon />} variant="contained" component="label">
                    {fileName}
                    <input onChange={onFileChange} hidden name="file1" accept="application/pdf" multiple type="file" />
                </Button>
                }
                </Grid>
                <Grid item>
                <Button onClick={handleUploadButton} sx={{ display: 'flex', width: 120, marginLeft: 1, backgroundColor: "#201F2B" }} edge="start" variant="contained" component="label">
                    Submit
                </Button>
                </Grid>
            </Grid>
            <Divider sx={{mt: 2}}/>
            <Button sx={{ backgroundColor: "#201F2B", mt: 2, ml: 1 }} variant="contained" onClick={handleDownloadButton}>Download</Button>
        </>
    )
}