import  { React,useEffect, useState } from 'react'
import {Button,ListGroup, ListGroupItem, Row, Col, Image} from 'react-bootstrap'

import axios from 'axios'
import Cookies from 'universal-cookie'
import {Link} from 'react-router-dom'

export default function Cart() {

  const cookies = new Cookies();
  const [carts, setCarts] = useState([])


  const handleCheck = async (cart) => {
    let check = false
    if (cart.check===true) {
      check = false
    } else {
       check = true
    }
    
    const result = await axios.put("http://localhost:8080/customer/cart",{
      'id':{'customerId':cart.id.customerId,'productId':cart.id.productId},'quantity':cart.quantity,'check':check
    }
    ,{
      headers:{
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + cookies.get('token'),
          'customerId': cookies.get('customerId')
      }
      })
      loadCarts()
  };
  const onInputChange = async (e)=>{
    let {value,name} = e.target
    let item = JSON.parse(name);
    let tempCart = [...carts]
    let findIndex = tempCart.findIndex((cart) => cart.id.productId == item.id.productId);
    if (e.target.value>0){
      tempCart[findIndex].quantity=value
      setCarts(tempCart)
      axios.put("http://localhost:8080/customer/cart",{
      'id':{'customerId':item.id.customerId,'productId':item.id.productId},'quantity':value,'check':item.check
    }
    ,{
      headers:{
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + cookies.get('token'),
          'customerId': cookies.get('customerId')
      }
      })
    }else{
      alert('Quantity can not be 0. Please delete')
    }
    
  }
  
  const [sum, setSum] = useState([])
  const [count, setCount] = useState([])
  useEffect(()=>{
    loadCarts();
  },[])
  useEffect(()=>{
    setSum(carts.filter(item=>item.check).reduce((total,currentItem)=> 
    total = total + currentItem.productPrice*currentItem.quantity,0))
  },[carts])
  useEffect(()=>{
    setCount(carts.filter(item=>item.check).reduce((total,currentItem)=> 
    total = total + 1,0))
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
  const deleteCarts = async(cartId)=>{
    axios.delete("http://localhost:8080/customer/cart",{
      headers:{
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + cookies.get('token'),
          'customerId': cookies.get('customerId')
      },
      data:{
        'customerId':cartId.customerId,'productId':cartId.productId
      }
  }).then((res)=>{
    alert(res.data.message)
    loadCarts()
  });
  }
  return (
    <div className='home'>
      <div className='productContainer'>
        <ListGroup>
          {
          carts.map((cart)=>{
            return (
              <ListGroupItem key={cart.id.productId}>
              <Row>
                <Col md={1}>
                <div className="form-check">
                <input class="form-check-input" checked={cart.check} type="checkbox" onChange={(e)=>handleCheck(cart)}/>
                </div>
                </Col>
                <Col md={1}>
                  <Image src={cart.productImageUrl} alt={cart.productName} fluid/>
                </Col>
                <Col md={3}>
                <span>{cart.productName}</span>
                </Col>
                <Col md={3}>
                <span>{cart.productPrice} đ</span>
                </Col>
                <Col md={1}>
                  <input 
                  type={"number"} 
                  className="form-control" 
                  name={JSON.stringify(cart)}
                  value={cart.quantity} 
                  onChange={(e)=> onInputChange(e)}
                  />
                </Col>
                <Col>
                <Button variant='danger' onClick={(e)=>deleteCarts(cart.id)}>X</Button>
                </Col>
                <Col>
                <span> Sum: {cart.productPrice*cart.quantity} đ</span>
                </Col>
              </Row>
            </ListGroupItem>
            )
          }
          ).reverse()}
        </ListGroup>
      </div>
      <div className='filters summary'>
          <span className='title'>Subtotal ({count}) products</span>
          <span style={{fontWeight:700, fontsize:20}}>Total price: {sum} đ</span>
          <Link to='/customer/confirm'>
            <Button type='button' disabled={carts.length === 0}>
              Create Order
            </Button>
          </Link>
      </div>
    </div>
  )
  
}
