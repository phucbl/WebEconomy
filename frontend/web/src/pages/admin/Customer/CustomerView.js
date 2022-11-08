import  { React,useEffect, useState } from 'react'
import axios from 'axios'
import { useParams, Link } from 'react-router-dom'
import Cookies from 'universal-cookie';
import {Button} from 'react-bootstrap'
export default function CustomerView() {
    const {id} = useParams();
    const cookies = new Cookies();
    const [customer, setCustomer] = useState({})
    useEffect(()=>{
        loadCustomer();
      },[])
    
      const loadCustomer = async()=>{
        const result=await axios.get(`http://localhost:8080/customer/${id}`,{
            headers:{
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + cookies.get('token'),
            }
        });
        setCustomer(result.data)
      }

  return (
    <div className='customer'>
        <div className='col-md-6 offset-md-3 border rounded p-4 mt-2'>
            <h1 className='text-center m-4'>Customer Info</h1>
            <h2>Phone Number: {customer.phoneNumber}</h2>
            <h2>Name: {customer.name}</h2>
            <h2>Address: {customer.address}</h2>
        </div>
        <div className='col text-center'>
        <Link to="/manager/customer">
            <Button>Back</Button>
        </Link>
        </div>
    </div>
  )
}
