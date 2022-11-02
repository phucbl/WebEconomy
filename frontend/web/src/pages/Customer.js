import  { React,useEffect, useState } from 'react'
import axios from 'axios'
import Cookies from 'universal-cookie';

export default function Customer() {
    const cookies = new Cookies();
    const [customer, setCustomer] = useState({})
    useEffect(()=>{
        loadCustomer();
      },[])
    
      const loadCustomer = async()=>{
        const result=await axios.get("http://localhost:8080/customer/",{
            headers:{
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + cookies.get('token'),
                'customerId': cookies.get('customerId')
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
    </div>
  )
}
