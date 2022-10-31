import  {React, useState } from 'react'
import axios from 'axios'
import {useNavigate,Link} from 'react-router-dom'
import { Button } from 'react-bootstrap'
// import {useCookies} from 'react-cookie'
import Cookies from 'universal-cookie'

export default function Login() {

    let navigate = useNavigate()

    const cookies = new Cookies();
  
    
    const [login,setLogin]=useState({
        phoneNumber:"",
        password:"",
    })
    const{phoneNumber,password}=login

    const onInputChange = (e)=>{
        setLogin({...login,[e.target.name]:e.target.value})
    }

    const onSubmit =async (e)=>{
        e.preventDefault();
        await axios.post("http://localhost:8080/customer/login",login)
        .then((res)=>{
          cookies.set('name',res.data.data.name,{ path: '/' })
          cookies.set('customerId',res.data.data.customerId,{ path: '/' })
          cookies.set('role',res.data.data.role,{ path: '/' })
          cookies.set('token',res.data.data.token,{ path: '/' })
          navigate("/")
        })
        .catch((error)=>{
          alert(error.data.message)
        })
        window.location.reload()
    };

  return (
    <div className='container'>
        <div className='row'>
          <div className='col-md-6 offset-md-2 border rounded p-4 mt-2'>
            <h2 className='text-center m-4'>Login</h2>
            <form onSubmit={(e)=>onSubmit (e)}>
            <div className='mb-3'>
              <label htmlFor="phoneNumber" className='form-label'>
                Phone Number
              </label>
              <input 
              type={"text"}
              className="form-control"
              name="phoneNumber"
              value={phoneNumber}
              onChange={(e)=> onInputChange(e)}/>

            </div>
            <div className='mb-3'>
              <label htmlFor="password" className='form-label'>
                Password
              </label>
              <input 
              type={"password"}
              className="form-control"
              name="password"
              value={password}
              onChange={(e)=> onInputChange(e)}/>
              
            </div>
            <div className='d-flex justify-content-center'>
              <Button type="submit">Login</Button>
              <Button variant="success" style={{marginLeft:10}}>
                <Link to='/customer/register'>Register New Accout</Link>
                </Button>
            </div>
            </form>  
          </div>

        </div>
    </div>
  )
}
