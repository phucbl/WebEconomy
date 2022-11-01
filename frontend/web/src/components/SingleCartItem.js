import  { React,useEffect,useState,setState} from 'react'
import {Card,Button, ListGroupItem, Row, Col, Image} from 'react-bootstrap'
import {Link} from 'react-router-dom'
import Cookies from 'universal-cookie';
import axios from 'axios';
import setSum from '../pages/Cart';
import Cart from '../pages/Cart';


export default function SingleCartItem ({cart})  {
    const cookies = new Cookies()
    const [itemSumPrice,setItemSumPrice] = useState(cart.product.price*cart.quantity)

    useEffect(()=>{
      deleteCarts();
    },[])
    useEffect(()=>{
      setItemSumPrice(cart.product.price*cart.quantity)
    },[cart])

    const onInputChange = async (e)=>{
      if (e.target.value>0){
        cart.quantity=e.target.value
        changeCarts(e)
        setItemSumPrice(cart.product.price*e.target.value)
        
      }else{
        alert('Quantity can not be 0. Please delete')
      }
      
    } 

    const changeCarts = async(e)=>{
      await axios.put("http://localhost:8080/customer/cart",{
        'id':{'customerId':cookies.get('customerId'),'productId':cart.product.id},'quantity':e.target.value
      }
      ,{
        headers:{
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + cookies.get('token'),
            'customerId': cookies.get('customerId')
        }
        })
    }
    const deleteCarts = async(e)=>{
      e.preventDefault()
      await axios.delete("http://localhost:8080/customer/cart",{
        headers:{
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + cookies.get('token'),
            'customerId': cookies.get('customerId')
        },
        data:{
          'customerId':cookies.get('customerId'),'productId':cart.product.id
        }
    }).then((res)=>{
      alert(res.data.message)
    });
    }
  return (
    <div className='carts'>
        <ListGroupItem key={cart.id}>
              <Row>
                <Col md={1}>
                <div className="form-check">
                <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault"/>
                </div>
                </Col>
                <Col md={1}>
                  <Image src={cart.product.imageUrl} alt={cart.product.name} fluid/>
                </Col>
                <Col md={3}>
                <span>{cart.product.name}</span>
                </Col>
                <Col md={3}>
                <span>{cart.product.price} đ</span>
                </Col>
                <Col md={1}>
                  <input 
                  type={"number"} 
                  className="form-control" 
                  name="quantity" 
                  value={cart.quantity} 
                  onChange={(e)=> onInputChange(e)}/>
                </Col>
                <Col>
                <Button variant='danger'onClick={(e)=>deleteCarts(e)}>X</Button>
                </Col>
                <Col>
                <span>{itemSumPrice} đ</span>
                </Col>
              </Row>
            </ListGroupItem>
    </div>
  )
}

