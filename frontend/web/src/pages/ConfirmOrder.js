import  {React, useEffect, useState } from 'react'
import axios from 'axios'
import {useNavigate,Link} from 'react-router-dom'
import {Button,ListGroup, ListGroupItem, Row, Col, Image} from 'react-bootstrap'
import Cookies from 'universal-cookie'
export default function ConfirmOrder () {
    const navigate=new useNavigate()
    const cookies = new Cookies();
    const [customer, setCustomer] = useState([])
    const [carts, setCarts] = useState([])
    const [sum, setSum] = useState([])
    const [count, setCount] = useState([])
    const [countItem,setCountItem] = useState([])
    useEffect(()=>{
        setCountItem(carts.filter(item=>item.check).reduce((total,currentItem)=> 
        total = total + currentItem.quantity,0))
    },[carts])
    useEffect(()=>{
        setSum(carts.filter(item=>item.check).reduce((total,currentItem)=> 
        total = total + currentItem.productPrice*currentItem.quantity,0))
    },[carts])
    useEffect(()=>{
        setCount(carts.filter(item=>item.check).reduce((total,currentItem)=> 
        total = total + 1,0))
    },[carts])
    useEffect(()=>{
        loadCustomer();
        loadCarts();
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
    const createOrder = (e)=>{
        e.preventDefault()
        axios.post("http://localhost:8080/customer/cart",{
                'customerId':cookies.get('customerId'),
                'carts':carts.filter(item=>item.check)
        },{
        headers:{
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + cookies.get('token'),
            'customerId': cookies.get('customerId')
        },
        
    }).then(()=>{
      navigate("/customer/order")
    });
      
    }
  return (
    <div className='customer'>
        <div className='col-md-6 offset-md-3 border rounded p-4 mt-2'>
            <h1 className='text-center m-4'>Confirm Order</h1>
            <h2>Phone Number: {customer.phoneNumber}</h2>
            <h2>Name: {customer.name}</h2>
            <h2>Address: {customer.address}</h2>
            <ListGroup>
              {
              carts.filter(item=>item.check).map((cart)=>{
                return (
                  <ListGroupItem key={cart.id.productId}>
                  <Row>
                    <Col md={1}>
                      <Image src={cart.productImageUrl} alt={cart.productName} fluid/>
                    </Col>
                    <Col md={3}>
                    <span>{cart.productName}</span>
                    </Col>
                    <Col md={3}>
                    <span>{cart.productPrice} đ</span>
                    </Col>
                    <Col md={3}>
                      <span>Số lượng: {cart.quantity} </span>
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
        <div className='col text-center'>
            <h3> Number of products : {count}</h3>
            <h3> Number of items: {countItem}</h3>
            <h3> Total price: {sum} đ</h3>
        </div>
        
        <div className='col text-center'>
            <Button variant="success" onClick={(e)=>createOrder(e)}>Confirm</Button>
            <Link to='/customer/cart'>
            <Button variant="danger" style={{marginLeft:10}}>Cancel</Button>
            </Link>
            
        </div>
        </div>
        
    </div>
  )
}