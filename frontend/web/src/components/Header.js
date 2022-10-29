import React from 'react'
import {FaShoppingCart} from 'react-icons/fa'
import {
    Container,FormControl,Navbar,
    Badge,Nav,Dropdown,Button,
} from 'react-bootstrap'
import { Link } from 'react-router-dom'
// import { CartState } from './Context'

export default function Header(){
    // const {state:{cart}}=CartState();
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
                    <Dropdown alignRight>
                        <Dropdown.Toggle variant="success">
                            <FaShoppingCart color="white" fontSize="25px"/>
                            <Badge>{0}</Badge>
                        </Dropdown.Toggle>
                        <Dropdown.Menu style={{minWidth:370}}>
                            <span style={{padding:10}}>Cart is empty!</span>
                        </Dropdown.Menu>
                    </Dropdown>
                    <Button style={{marginLeft:10}}>
                        <Link to="/customer/login">Login</Link>
                    </Button>
                </Nav>
                
            </Container>
        </Navbar>
        
    )
};
