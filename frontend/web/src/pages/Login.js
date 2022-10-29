import  {React, useState } from 'react'
import axios from 'axios'
import {useNavigate} from 'react-router-dom'

export default function Login() {

    // let navigate = useNavigate()
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
        <div>Login</div>
    </div>
  )
}
