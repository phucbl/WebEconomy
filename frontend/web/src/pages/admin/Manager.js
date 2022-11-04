import React from 'react'
import Cookies from 'universal-cookie'
import { useNavigate, Link } from 'react-router-dom'

export default function HomeManager() {
    const cookies = new Cookies()
    const navigate = new useNavigate()
    
  return (
        <div className="container">
                <h1 className='text-center' style={{padding:100}}>Home Manager</h1>
            <div className="row">
                <Link to="/manager/customer" className="col m-2">
                <div className="bg-danger text-dark text-center border rounded" style={{padding:50}}><h1>Customer</h1></div>
                </Link>
                <Link to="/manager/category" className="col m-2">
                <div className="bg-warning text-dark text-center border rounded" style={{padding:50}}><h1>Category</h1></div>
                </Link>
                <div className="w-100"></div>
                <Link to="/manager/product" className="col m-2">
                <div className="bg-info text-white text-center border rounded" style={{padding:50}}><h1>Product</h1></div>
                </Link>
                <Link to="/manager/order" className="col m-2">
                <div className="bg-dark text-white text-center border rounded" style={{padding:50}}><h1>Order</h1></div>
                </Link>
            </div>
        </div>
  )
}
