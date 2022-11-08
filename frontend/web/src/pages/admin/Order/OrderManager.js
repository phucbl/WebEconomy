import  { React,useEffect, useState } from 'react'
import {Button} from 'react-bootstrap'
import {Link} from 'react-router-dom'
import axios from 'axios'
import Cookies from 'universal-cookie'

export default function OrderManager(){
    const cookies = new Cookies();
    const [orders, setOrders] = useState([])
    useEffect(()=>{
        loadOrders();
      },[])
    const loadOrders = ()=>{
        axios.get("http://localhost:8080/order",{
          headers:{
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + cookies.get('token')
          }
      }).then((res)=>{
        setOrders(res.data);
      })}
    return(
        <div className='col-md-8 offset-md-2 border rounded p-4 mt-2'>
            
            <table class="table table-striped">
                <thead>
                    <tr>
                    <th scope="col" className='text-center m-4'>#</th>
                    <th scope="col" className='text-center m-4'>Date Created</th>
                    <th scope="col" className='text-center m-4'>Status</th>
                    </tr>
                </thead>
                <tbody>
                {
                    orders.sort((a, b) => a.id - b.id).map((order)=>(
                        <tr>
                            <th scope="row" className='text-center m-4' key={order.id}>{order.id}</th>
                            <td className='text-center m-4'>{order.dateCreated}</td>
                            <td className='text-center m-4'>
                                {order.status==="CHECKING"?(
                                    <div class="alert alert-warning" role="alert">
                                    CHECKING
                                  </div>
                                ):(order.status==="SHIPPING"?(
                                    <div class="alert alert-info" role="alert">
                                    SHIPPING
                                  </div>
                                ):(order.status==="DONE"?(
                                    <div class="alert alert-success" role="alert">
                                    DONE
                                  </div>
                                ):(<div class="alert alert-danger" role="alert">
                                        CANCEL
                                    </div>
                                    
                                )))}
                            </td>
                            <td className='text-center m-4'>
                                <Link to={`/manager/order/${order.id}`}>
                                    <Button>View Detail</Button>
                                </Link>
                                <Link to={`/manager/order/edit/${order.id}`}>
                                    <Button variant="warning" style={{marginLeft:10}}>Edit</Button>
                                </Link>
                                
                            </td>
                        </tr>
                    )).reverse()
                }
                </tbody>
                
            </table>
        </div>
    )
}