import  { React,useEffect, useState } from 'react'
import { useParams, Link } from 'react-router-dom'

import {Button,ListGroup, ListGroupItem, Row, Col, Image} from 'react-bootstrap'
import axios from 'axios';
import "../components/style.css";
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
      loadCustomer();
      
      },[])
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
        axios.get(`http://localhost:8080/customer/order/${id}`,{
          headers:{
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + cookies.get('token'),
              'customerId': cookies.get('customerId')
          }
      }).then((res)=>{
        setOrder(res.data)
        setOrderDetails(res.data.orderDetailResponseDtos)
    })}
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
    const changeStatus = (value)=>{
      order.status=value
      setOrder(order)
      axios.put(`http://localhost:8080/order/${order.id}`,{
        "status":value
      },{
        headers:{
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + cookies.get('token')
        }
        }).then((res)=>{
          loadOrder()
        })
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
          <div className="alert alert-warning" role="alert">
          CHECKING
        </div>
      ):(order.status=="SHIPPING"?(
          <div className="alert alert-info" role="alert">
          SHIPPING
        </div>
      ):(order.status=="DONE"?(
          <div className="alert alert-success" role="alert">
          DONE
        </div>
      ):(<div className="alert alert-danger" role="alert">
              CANCEL
          </div>
      )))}
      </div>
      </Col>
      <div className='col text-center' hidden={order.status==="DONE"||order.status==="CANCEL"}>
        <h2>Cancel Order</h2>
        <Button variant='danger' style={{margin:10}} data-toggle="modal" data-target="#modal1" >CANCEL</Button>
      </div>
      <div className="modal fade" id="modal1" tabIndex="-1" role="dialog" aria-labelledby="modal1Label" aria-hidden="true">
      <div className="modal-dialog" role="document">
          <div className="modal-content">
          <div className="modal-header">
          <h5 className="modal-title" id="modal1Label">Cancel Order</h5>
              <button type="button" className="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
              </button>
          </div>
          <div className="modal-body">
              You want to cancel order: {order.id}?
          </div>
          <div className="modal-footer text-center">
              <button type="button" className="btn btn-primary" style={{paddingLeft:20,paddingRight:20,marginRight:50}}
              onClick={()=>changeStatus("CANCEL")} data-dismiss="modal"
              >Cancel</button>
              <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
              
          </div>
          </div>
      </div>
      </div>
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
      <Link to="/customer/order">
        <Button>Back</Button>
      </Link>
      </div>
      
        
  </div>
)
}