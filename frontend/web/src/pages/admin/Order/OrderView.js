import  { React,useEffect, useState } from 'react'
import { useParams, Link } from 'react-router-dom'
import {Button,ListGroup, ListGroupItem, Row, Col, Image} from 'react-bootstrap'
import axios from 'axios';
import Cookies from 'universal-cookie';
export default function ViewOrder() {
    const cookies = new Cookies()
    const {id} = useParams();
    const [customer, setCustomer] = useState([])
    const [order,setOrder] = useState([])
    const [orderDetails, setOrderDetails] = useState([])
    const [sum, setSum] = useState([])
    const [count, setCount] = useState([])
    const [countItem,setCountItem] = useState([])
    useEffect(()=>{
      loadOrder();
      },[])
    useEffect(()=>{
        loadCustomer();
        },[order])
    useEffect(()=>{
      setCountItem(orderDetails.reduce((total,currentItem)=> 
      total = total + currentItem.quantity,0))
    },[orderDetails])
    useEffect(()=>{
        setSum(orderDetails.reduce((total,currentItem)=> 
        total = total + currentItem.price*currentItem.quantity,0))
    },[orderDetails])
    useEffect(()=>{
        setCount(orderDetails.reduce((total,currentItem)=> 
        total = total + 1,0))
    },[orderDetails])
    
    const loadOrder = ()=>{
        axios.get(`http://localhost:8080/order/${id}`,{
          headers:{
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + cookies.get('token')
          }
      }).then((res)=>{
        setOrder(res.data)
        setOrderDetails(res.data.orderDetailResponseDtos)
      })}
    const loadCustomer = ()=>{
      axios.get(`http://localhost:8080/customer/${order.customerId}`,{
          headers:{
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + cookies.get('token')
          }
      }).then((res)=>{
        setCustomer(res.data)
      });
      
      }
return (
  <div className='col-md-8 offset-md-2 border rounded p-4 mt-2'>
    <Row className='text-center m-4'>
      <Col md={2}></Col>
    <Col md={8}>
      <h1 className='text-center m-4'>Order: {id} </h1>
      </Col>
      <Col md={2}>
      <div>
      {order.status=="CHECKING"?(
          <div class="alert alert-warning" role="alert">
          CHECKING
        </div>
      ):(order.status=="SHIPPING"?(
          <div class="alert alert-info" role="alert">
          SHIPPING
        </div>
      ):(order.status=="DONE"?(
          <div class="alert alert-success" role="alert">
          DONE
        </div>
      ):(<div class="alert alert-danger" role="alert">
              CANCEL
          </div>
      )))}
      </div>
      </Col>
    </Row>
      <h2>Phone Number: {customer.phoneNumber}</h2>
      <h2>Name: {customer.name}</h2>
      <h2>Address: {customer.address}</h2>
      <h2>Date created: {order.dateCreated} </h2>
      <ListGroup>
              {
              orderDetails.map((orderDetail)=>{
                return (
                  <ListGroupItem key={orderDetail.id.productId}>
                  <Row>
                    <Col md={1}>
                      <Image src={orderDetail.imageUrl} alt={orderDetail.productName} fluid/>
                    </Col>
                    <Col md={3}>
                    <span>{orderDetail.productName}</span>
                    </Col>
                    <Col md={3}>
                    <span>{orderDetail.price} ??</span>
                    </Col>
                    <Col md={3}>
                      <span>S??? l?????ng: {orderDetail.quantity} </span>
                    </Col>
                    <Col>
                    <span> Sum: {orderDetail.price*orderDetail.quantity} ??</span>
                    </Col>
                  </Row>
                </ListGroupItem>
                )
              }
              )}
      </ListGroup>
      <div className='col text-center'>
      <h3> Number of products : {count}</h3>
      <h3> Number of items: {countItem}</h3>
      <h3> Total price: {sum} ??</h3>
      </div>
      <div className='col text-center'>
      <Link to="/manager/order">
        <Button>Back</Button>
      </Link>
      </div>
      
        
  </div>
)
}