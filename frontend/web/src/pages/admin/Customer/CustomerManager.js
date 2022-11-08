import  { React,useEffect, useState } from 'react'
import {Button,Image} from 'react-bootstrap'
import {Link} from 'react-router-dom'
import axios from 'axios'
import Cookies from 'universal-cookie'

export default function CustomerManager(){
    const cookies = new Cookies();
    const [customers, setCustomers] = useState([])
    useEffect(()=>{
        loadCustomers();
      },[])
    const loadCustomers = ()=>{
        axios.get("http://localhost:8080/customer",{
          headers:{
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + cookies.get('token')
          }
      }).then((res)=>{
        setCustomers(res.data);
      })}
    const deactiveCustomer = (id)=>{
        axios.delete(`http://localhost:8080/account/${id}`,{
          headers:{
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + cookies.get('token')
          }
      }).then(()=>{loadCustomers()})
    }
    
    return(
        <div className='col-md-6 offset-md-3 border rounded p-4 mt-2'>
            
            <table className="table table-striped">
                <thead>
                    <tr>
                    <th scope="col" className='text-center m-4' style={{paddingLeft:150}}>#</th>
                    <th scope="col" className='text-center m-4'>Customer Name</th>
                    <th scope="col" className='text-center m-4'>Phone Number</th> 
                    </tr>
                </thead>
                <tbody>
                {
                    customers.sort((a, b) => a.id - b.id).map((customer)=>(
                        <tr key={customer.id}>
                            <th scope="row" className='text-center m-4' style={{paddingLeft:150}} >{customer.id}</th>
                            
                            <td className='text-center m-4'>{customer.name}</td>
                            <td className='text-center m-4'>{customer.phoneNumber}</td>
                            <td className='text-center m-4'>
                                
                                <Link to={`/manager/customer/${customer.id}`}>
                                <Button variant="warning" style={{marginLeft:10,marginRight:10,paddingLeft:30,paddingRight:30}} data-toggle="modal" data-target="#modal3"
                                >View</Button>
                                </Link>
                                
                                
                                {customer.status?(
                                    <Button variant="danger"  data-toggle="modal" data-target="#modal1">Deactive</Button>
                                ):(
                                    <Button variant='success' data-toggle="modal" data-target="#modal2">Active</Button>
                                )}
                                <div className="modal fade" id="modal1" tabIndex="-1" role="dialog" aria-labelledby="modal1Label" aria-hidden="true">
                                <div className="modal-dialog" role="document">
                                    <div className="modal-content">
                                    <div className="modal-header">
                                    <h5 className="modal-title" id="modal1Label">Deactive Customer</h5>
                                        <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div className="modal-body">
                                        You want to deactive customer: {customer.phoneNumber}?
                                    </div>
                                    <div className="modal-footer text-center">
                                        <button type="button" className="btn btn-primary" style={{paddingLeft:20,paddingRight:20,marginRight:50}}
                                        onClick={()=>deactiveCustomer(customer.accountId)} data-dismiss="modal"
                                        >Deactive</button>
                                        <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
                                        
                                    </div>
                                    </div>
                                </div>
                                </div>
                                <div className="modal fade" id="modal2" tabIndex="-1" role="dialog" aria-labelledby="modal2Label" aria-hidden="true">
                                <div className="modal-dialog" role="document">
                                    <div className="modal-content">
                                    <div className="modal-header">
                                    <h5 className="modal-title" id="modal2Label">Active Customer</h5>
                                        <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div className="modal-body">
                                        You want to active customer: {customer.phoneNumber}?
                                    </div>
                                    <div className="modal-footer text-center">
                                        <button type="button" className="btn btn-primary" style={{paddingLeft:20,paddingRight:20,marginRight:50}}
                                        onClick={()=>deactiveCustomer(customer.accountId)} data-dismiss="modal"
                                        >Active</button>
                                        <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
                                        
                                    </div>
                                    </div>
                                </div>
                                </div>
                            </td>
                        </tr>
                    )).reverse()
                }
                </tbody>
                
            </table>
            
        </div>
        
    )
}