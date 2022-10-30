import  {React, useState } from 'react'
import axios from 'axios'
import {useNavigate,Link} from 'react-router-dom'
import { Button } from 'react-bootstrap'

export default function Login() {

    let navigate = useNavigate()
    // const{phoneNumber,password}=login
    // const [login,setLogin]=useState({
    //     phoneNumber:"",
    //     password:"",
    // })

    // const onInputChange = (e)=>{
    //     setLogin({...login,[e.target.name]:e.target.value})
    // }

    // const onSubmit =async (e)=>{
    //     e.preventDefault();
    //     // await axios.post("http://localhost:8080/customer/login",login)
    //     navigate("/")
    // };

  return (
    <div className='container'>
        <div className='row'>
          <div className='col-md-6 offset-md-2 border rounded p-4 mt-2'>
            <h2 className='text-center m-4'>Login</h2>
            <div className='mb-3'>
              <label htmlFor="phoneNumber" className='form-label'>
                Phone Number
              </label>
              <input 
              type={"text"}
              className="form-control"
              name="phoneNumber"/>

            </div>
            <div className='mb-3'>
              <label htmlFor="password" className='form-label'>
                Password
              </label>
              <input 
              type={"password"}
              className="form-control"
              name="password"/>
              
            </div>
            <div className='mb-3'>
              <label htmlFor="password" className='form-label'>
                Retype Password
              </label>
              <input 
              type={"password"}
              className="form-control"
              name="password2"/>
              
            </div>
            <div className='mb-3'>
              <label htmlFor="Name" className='form-label'>
                Name
              </label>
              <input 
              type={"text"}
              className="form-control"
              name="name"/>

            </div>
            <div className='mb-3'>
              <label htmlFor="Address" className='form-label'>
                Address
              </label>
              <input 
              type={"text"}
              className="form-control"
              name="address"/>

            </div>
            <div className='d-flex justify-content-center'>
              <Button>Register</Button>
            </div>
            
          </div>

        </div>
    </div>
  )
}
