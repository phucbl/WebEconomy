import React from 'react'
import Cookies from 'universal-cookie'
import { useNavigate, Link } from 'react-router-dom'

export default function HomeManager() {
    const cookies = new Cookies()
    const navigate = new useNavigate()
    
  return (
    <div>
        {cookies.get('role')!=="ROLE_ADMIN"?(
            navigate('/')
        ):(
            <div class="container">
                <h1 className='text-center' style={{padding:100}}>Home Manager</h1>
            <div class="row">
                <Link to="/manager/customer/" class="col m-2">
                <div class="bg-danger text-dark text-center border rounded" style={{padding:50}}><h1>Customer</h1></div>
                </Link>
                <Link to="/manager/category/" class="col m-2">
                <div class="bg-warning text-dark text-center border rounded" style={{padding:50}}><h1>Category</h1></div>
                </Link>
                <div class="w-100"></div>
                <Link to="/manager/product/" class="col m-2">
                <div class="bg-info text-white text-center border rounded" style={{padding:50}}><h1>Product</h1></div>
                </Link>
                <Link to="/manager/order/" class="col m-2">
                <div class="bg-dark text-white text-center border rounded" style={{padding:50}}><h1>Order</h1></div>
                </Link>
            </div>
            </div>
            
        )}

    </div>
    
    
  )
}
