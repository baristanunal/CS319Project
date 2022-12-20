import React from 'react'
import DatePicker from '../../components/DatePicker'
import StyledTable from '../../components/StyledTable'

const headers = [
    ["Deadline", "Start", "End"],
]

const rows = [
    ["Pre Approval", <Date/>, <Date/>],
    ["Course Transfer", <Date/>, <Date/>],

]

function Date() {
    return (
        <div style={{marginLeft: 220}}>
            <DatePicker/>
        </div>
    )
    
}

const width = 1200

export default function Deadlines() {
  return (
    <div>
      <h1>Deadlines</h1>
      <StyledTable headers={headers} rows={rows} width={width}/>
    </div>
  )
}
