import  { React,useEffect, useState } from 'react'
import {FaShoppingCart} from 'react-icons/fa'
import {
    Container,FormControl,Navbar,
    Badge,Nav,Dropdown,Button,
} from 'react-bootstrap'
import { Link, useNavigate, useLocation  } from 'react-router-dom'
import axios from 'axios'
import Cookies from 'universal-cookie'


export default function Header(){

    let navigate = useNavigate()
    const location = useLocation();
    const path=location.pathname
    const cookies = new Cookies();

  const logout = async(e) => {
    
    cookies.remove('name', { path: '/' })
    cookies.remove('customerId', { path: '/' })
    cookies.remove('role', { path: '/' })
    cookies.remove('token', { path: '/' })
    navigate("/")
    window.location.reload()
}
    return(
        <Navbar bg="dark" variant="dark" style={{height:80}}>
            {path=="/customer/login"?(
                    console.log(path)
                ):( 
                    
                    cookies.get('role')!="ROLE_ADMIN"?(
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
                            <Link to='/customer/cart'>
                            <Button variant='success'>
                                <FaShoppingCart color="white" fontSize="25px"/> Cart
                            </Button>
                            </Link>
                            
                            {cookies.get('name')==null ? (
                                <Button style={{marginLeft:10}}>
                                <Link to="/customer/login">Login</Link>
                                </Button>
                            ):(
                                <Dropdown style={{marginLeft:10}}>
                                    <Dropdown.Toggle>
                                        {cookies.get('name')}
                                    </Dropdown.Toggle>
                                    <Dropdown.Menu>
                                    <a class="dropdown-item" href='/customer'>Info</a>
                                    <a class="dropdown-item" href='/customer/order'>Orders</a>
                                    <Dropdown.Divider/>
                                    <a class="dropdown-item" onClick={logout}>Log Out</a>
                                    </Dropdown.Menu>
                                </Dropdown>
                            )}
                            
                        </Nav>
                        
                    </Container>
                    ):( 
                        <Container>
                        <Navbar.Brand className='shopName'>
                            <Link to="/manager">PohucSecur</Link>
                        </Navbar.Brand>
                        
                        <Dropdown style={{marginLeft:10}}>
                            console.log(path)
                            <Dropdown.Toggle>
                                {cookies.get('name')}
                            </Dropdown.Toggle>
                            <Dropdown.Menu>
                            <a class="dropdown-item" onClick={logout}>Log Out</a>
                            </Dropdown.Menu>
                        </Dropdown>
                        </Container>
                    )
                )}
            
            
            
        </Navbar>
        
    )
};
