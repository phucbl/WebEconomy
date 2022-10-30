import  { React,useEffect, useState } from 'react'
import { Button, ListGroup } from 'react-bootstrap'
import axios from 'axios'
import Cookies from 'universal-cookie'

export default function Cart() {
  const cookies = new Cookies();
    const [carts, setCarts] = useState([])
    const [total, setTotal] = useState()
  useEffect(()=>{
    loadCarts();

  },[])

  const loadCarts = async()=>{
    const result=await axios.get("http://localhost:8080/customer/cart",{
      headers:{
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + cookies.get('token'),
          'customerId': cookies.get('customerId')
      }
  });
    setCarts(result.data);
    console.log(carts)
  }
  return (
    <div className='home'>
      <div className='productContainer'>
        <ListGroup>
          {
          carts.map((cart)=>(
            <span>{cart.id.productId}</span>
          ))
          }
        </ListGroup>
      </div>
      <div className='filters summary'>
          <span className='title'>Subtotal ({carts.length}) items</span>
          <span style={{fontWeight:700, fontsize:20}}>Total: 0</span>
          <Button type='button' disabled={carts.length === 0}>
            Creat Order
          </Button>
      </div>
    </div>
  )
}
