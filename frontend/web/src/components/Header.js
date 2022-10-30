import  { React,useEffect, useState } from 'react'
import {FaShoppingCart} from 'react-icons/fa'
import {
    Container,FormControl,Navbar,
    Badge,Nav,Dropdown,Button,
} from 'react-bootstrap'
import { Link } from 'react-router-dom'
import axios from 'axios'
import Cookies from 'universal-cookie'
// import { CartState } from './Context'

export default function Header(){
    const cookies = new Cookies();
    const [carts, setCarts] = useState([]);

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
  }
    return(
        <Navbar bg="dark" variant="dark" style={{height:80}}>
            <Container>
                <Navbar.Brand className='shopName'>
                    <Link to="/">PohucSecur</Link>
                </Navbar.Brand>
                <Navbar.Text className='search'>
                    <FormControl
                    style = {{width:600}}
                    placeholder='Search products'
                    className='m-auto'/>
                </Navbar.Text>
                <Nav>
                    <Button href='/customer/cart' variant='success'>
                        <FaShoppingCart color="white" fontSize="25px"/>
                        <span class="badge badge-light">{carts.length}</span>
                        
                    </Button>
                    
                    <Button style={{marginLeft:10}}>
                        <Link to="/customer/login">Login</Link>
                    </Button>
                </Nav>
                
            </Container>
        </Navbar>
        
    )
};
