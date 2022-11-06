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
          navigate('/')
          window.location.reload()
        })
        .catch((error)=>{
          alert(error.response.data.message)
        })
        
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
              onChange={(e)=> onInputChange(e)}
              />
              <div class="text-danger" hidden={phoneNumber!==""}>Please fill out this field.</div>
              <div class="text-danger" hidden={!isNaN(Number(phoneNumber)) }>Not meet phone number type</div>
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
              <div class="text-danger" hidden={password!==""}>Please fill out this field.</div>
            </div>
            <div className='d-flex justify-content-center'>
              <Button type="submit" style={{padding:'20px 50px'}} 
              disabled={
                isNaN(Number(phoneNumber))||
                phoneNumber===""||
                password===""
              }
              >Login</Button>
            </div>
            <div className='text-center' style={{marginTop:10}}>
            <Button variant="success" style={{marginRight:10}}>
                <Link to='/customer/register'>Register New Accout</Link>
                </Button>
            <Link to="/">
              <Button variant='danger' style={{paddingLeft:30,paddingRight:30}}>Back</Button>
              </Link>
            </div>
            </form>  
          </div>

        </div>
    </div>
  )
}
