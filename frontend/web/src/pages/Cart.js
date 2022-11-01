import  { React,useEffect, useState } from 'react'
import { Button, ListGroup } from 'react-bootstrap'
import axios from 'axios'
import Cookies from 'universal-cookie'
import SingleCartItem from '../components/SingleCartItem'

export default function Cart() {

  const cookies = new Cookies();
    const [carts, setCarts] = useState([])
    let [sum, setSum] = useState([])
    
  useEffect(()=>{
    loadCarts();
  },[carts])
  useEffect(()=>{
    setSum(carts.reduce((total,currentItem)=> total = total + currentItem.product.price*currentItem.quantity,0))
  },[carts])

  const loadCarts = async()=>{
    const result=await axios.get("http://localhost:8080/customer/cart",{
      headers:{
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + cookies.get('token'),
          'customerId': cookies.get('customerId')
      }
  });
    setCarts(result.data);
  }
  return (
    <div className='home'>
      <div className='productContainer'>
        <ListGroup>
          {
          carts.map((cart)=>{
            return <SingleCartItem cart = {cart} key = {cart.product.id}/>
          }
          ).reverse()}
        </ListGroup>
      </div>
      <div className='filters summary'>
          <span className='title'>Subtotal ({carts.length}) products</span>
          <span style={{fontWeight:700, fontsize:20}}>Total price: {sum} đ</span>
          <Button type='button' disabled={carts.length === 0}>
            Creat Order
          </Button>
      </div>
    </div>
  )
  
}
