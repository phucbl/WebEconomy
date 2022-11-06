import  {React, useEffect, useState } from 'react'
import axios from 'axios'
import {useNavigate,Link} from 'react-router-dom'
import { Button } from 'react-bootstrap'

export default function Login() {
    let navigate = useNavigate()
    const [retypePassword,setRetypePassword]=useState("")
    const [isSubmitClicked,setIsSubmitClicked]=useState([false])
    const [newAccount,setNewAccount]=useState({
        phoneNumber:"",
        password:"",
        name:"",
        address:"",
    })
    const{phoneNumber,password,name,address}=newAccount
    const onInputChange = (e)=>{
      setNewAccount({...newAccount,[e.target.name]:e.target.value})
      console.log(isSubmitClicked)
    }
    const onSubmit =async (e)=>{
        e.preventDefault();
        setIsSubmitClicked(true)
        
        if(
          isNaN(Number(phoneNumber))||
          phoneNumber===""||
          password===""||
          name===""||
          address===""
          )
          {
            alert("Input not valid")
          }else{
            axios.get(`http://localhost:8080/customer/check/${phoneNumber}`)
            .then(()=>{
              axios.post("http://localhost:8080/customer/register",newAccount)
              .then(()=>{
                alert("Register Successfully")
                navigate("/customer/login")
              })
              .catch((error)=>alert(error.response.data.message))
            })
            .catch(()=>{ 
                alert("Phone number is already signed up")
            })
          }
          
        
        
        
    };

  return (
    <div className='container'>
        <div className='row'>
          <div className='col-md-6 offset-md-2 border rounded p-4 mt-2'>
            <h2 className='text-center m-4'>Register</h2>
            <form onSubmit={(e)=>onSubmit (e)}>
            <div className='mb-3'>
              <label htmlFor="phoneNumber" className='form-label'>
                Phone Number
              </label>
              <input 
              type="text"
              className="form-control"
              name="phoneNumber"
              value={phoneNumber}
              onChange={(e)=> onInputChange(e)}
              />
              <div class="text-danger" hidden={isSubmitClicked!==true||phoneNumber!==""}>Please fill out this field.</div>
              <div class="text-danger" hidden={isSubmitClicked!==true||!isNaN(Number(phoneNumber)) }>Not meet phone number type</div>
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
              onChange={(e)=> onInputChange(e)}
              
              />
              <div class="text-danger" hidden={isSubmitClicked!==true||password!==""}>Please fill out this field.</div>
            </div>
            <div className='mb-3'>
              <label htmlFor="password" className='form-label'>
                Retype Password
              </label>
              <input 
              type={"password"}
              className="form-control"
              value={retypePassword}
              onChange={(e)=> setRetypePassword(e.target.value)}
              name="password2"
              
              />
            <div class="text-danger" hidden={isSubmitClicked!==true||password===""||password===retypePassword}>Not match password</div>
            <div class="text-danger" hidden={isSubmitClicked!==true||retypePassword!==""}>Please fill out this field.</div>
            </div>
            <div className='mb-3'>
              <label htmlFor="Name" className='form-label'>
                Name
              </label>
              <input 
              type={"text"}
              className="form-control"
              name="name"
              value={name}
              onChange={(e)=> onInputChange(e)}
              />
              <div class="text-danger" hidden={isSubmitClicked!==true||name!==""}>Please fill out this field.</div>
            </div>
            <div className='mb-3'>
              <label htmlFor="Address" className='form-label'>
                Address
              </label>
              <input 
              type={"text"}
              className="form-control"
              name="address"
              value={address}
              onChange={(e)=> onInputChange(e)}
              />
              <div class="text-danger" hidden={isSubmitClicked!==true||address!==""}>Please fill out this field.</div>
            </div>
            <div className='d-flex justify-content-center'>
              <Button
              
              type="submit"
              style={{marginRight:10}}
              >Register</Button>
              <Link to="/customer/login">
              <Button variant='danger'>Back</Button>
              </Link>
            </div>
            </form>
          </div>

        </div>
    </div>
  )
}
