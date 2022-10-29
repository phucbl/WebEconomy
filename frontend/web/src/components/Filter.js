import React from 'react'
import { Form, Button } from 'react-bootstrap'

const Filter = () => {
  return (
    <div className='filters'>
        <span className='title'>Filter Products</span>
        <span>
            <Form.Check
            inline
            label="Ascending"
            name="group1"
            type="radio"
            id={'inline-1'}
            />
        </span>
        <span>
            <Form.Check
            inline
            label="Descending"
            name="group1"
            type="radio"
            id={'inline-2'}
            />
        </span>
        <span>
            <Form.Check
            inline
            label="Linh kiện mạch điện"
            name="group1"
            type="checkbox"
            id={'inline-3'}
            />
        </span>
        <span>
            <Form.Check
            inline
            label="Linh kiện mạch hotend"
            name="group1"
            type="checkbox"
            id={'inline-4'}
            />
        </span>
        
        <Button variant="light">Clear Filters</Button>
    </div>
  )
}

export default Filter