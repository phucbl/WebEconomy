import React from 'react'
import { Form, Button } from 'react-bootstrap'
import { FilterState } from '../context/FilterContext'

const Filter = () => {
    const{
        state:{ byCate1,byCate2,byCate3,byCate4,sort},
        dispatch
    }=FilterState()
  return (
    <div className='filters'>
        <span className='title'>Filter Products</span>
        <span>
            <Form.Check
            inline
            label="Price increase"
            name="group1"
            type="radio"
            id={'inline-1'}
            onChange={() =>
                dispatch({
                  type: "SORT_BY_PRICE",
                  payload: "lowToHigh",
                })
              }
              checked={sort === "lowToHigh" ? true : false}
            />
        </span>
        <span>
            <Form.Check
            inline
            label="Price descrease"
            name="group1"
            type="radio"
            id={'inline-2'}
            onChange={() =>
                dispatch({
                  type: "SORT_BY_PRICE",
                  payload: "highToLow",
                })
              }
              checked={sort === "highToLow" ? true : false}
            />
        </span>
        <span>
            <Form.Check
            inline
            label="Sort by number sold"
            name="group1"
            type="radio"
            id={'inline-3'}
            onChange={() =>
                dispatch({
                  type: "SORT_BY_COUNT",
                  payload: "count",
                })
              }
              checked={sort === "count" ? true : false}
            />
        </span>
        <span>
            <Form.Check
            inline
            label="Linh kiện mạch điện"
            name="group1"
            type="checkbox"
            id={'inline-3'}
            onChange={() =>
                dispatch({
                  type: "FILTER_BY_CATE1"
                })
              }
              checked={byCate1}
            />
        </span>
        <span>
            <Form.Check
            inline
            label="Linh kiện mạch hotend"
            name="group1"
            type="checkbox"
            id={'inline-4'}
            onChange={() =>
                dispatch({
                  type: "FILTER_BY_CATE2"
                })
              }
              checked={byCate2}
            />
        </span>
        <span>
            <Form.Check
            inline
            label="Ốc, vít"
            name="group1"
            type="checkbox"
            id={'inline-5'}
            onChange={() =>
                dispatch({
                  type: "FILTER_BY_CATE3"
                })
              }
              checked={byCate3}
            />
        </span>
        <span>
            <Form.Check
            inline
            label="Phụ kiện khác"
            name="group1"
            type="checkbox"
            id={'inline-6'}
            onChange={() =>
                dispatch({
                  type: "FILTER_BY_CATE4"
                })
              }
              checked={byCate4}
            />
        </span>
        
        <Button variant="light" 
            onClick={() =>
            dispatch({
                type: "CLEAR_FILTER",
            })}
          >Clear Filters</Button>
    </div>
  )
}

export default Filter