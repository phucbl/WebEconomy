import  { React,useEffect, useState } from 'react'
import axios from 'axios'
import Cookies from 'universal-cookie';
import { Button } from 'react-bootstrap';

export default function Customer() {
    const cookies = new Cookies();
    const [editName,setEditName] = useState([])
    const [editAddress,setEditAddress] = useState([])
    const [customer, setCustomer] = useState([])
    const [isSubmitClicked,setIsSubmitClicked]=useState([false])
    const [isPasswordWrong,setIsPasswordWrong]=useState([false])
    const [password,setPassword] = useState([])
    const [newPassword,setNewPassword] = useState([])
    const [retypePassword,setRetypePassword] = useState([])
    useEffect(()=>{
        loadCustomer();
      },[])
    
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
    const changeName = (e) =>{
      setEditName (e.target.value)
    }
    const changeAddress = (e) =>{
      setEditAddress (e.target.value)
    }
    const editCustomer = (id)=>{
      axios.put(`http://localhost:8080/customer/${id}`,{
          'accountId':customer.accountId,
          'name':editName,
          'address':editAddress
      },{
        headers:{
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + cookies.get('token')
        }
    }).then(()=>{
      loadCustomer()
      setEditName([])
      setEditAddress([])
      })
    
  }
  const changPassword = (id)=>{
    setIsSubmitClicked(true)
    if(
        password===""||
        newPassword===""||
        retypePassword===""
        )
        {
          alert("Input not valid")
        }else{
            axios.put(`http://localhost:8080/customer/password`,{
                'password':password,
                'newPassword':newPassword
            },{
            headers:{
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + cookies.get('token')
            }
        }).then((res)=>{
            alert(res.data.message)
            setPassword([])
            setNewPassword([])
            setRetypePassword([])
            }).catch((error)=>{
            alert(error.response.data.message)
            })
    }
  }
  return (
    <div className='customer'>
        <div className='col-md-6 offset-md-3 border rounded p-4 mt-2'>
            <h1 className='text-center m-4'>Customer Info</h1>
            <h2>Phone Number: {customer.phoneNumber}</h2>
            <h2>Name: {customer.name}</h2>
            <h2>Address: {customer.address}</h2>
        </div>
        <div className='text-center'>
            <Button style={{padding:20,margin:20}} data-toggle="modal" data-target="#modal1">Edit Info</Button>
            
            <Button style={{padding:20,margin:20}} data-toggle="modal" data-target="#modal2">Change Password</Button>

            <div className="modal fade" id="modal1" tabIndex="-1" role="dialog" aria-labelledby="modal1Label" aria-hidden="true">
            <div className="modal-dialog" role="document">
                <div className="modal-content">
                <div className="modal-header">
                    <h5 className="modal-title" id="modal1Label">Edit Info : {customer.phoneNumber}</h5>
                    <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div className="modal-body">
                <label htmlFor="categoryName" className='form-label'>
                    Name
                </label>
                <input 
                type={"text"}
                className="form-control"
                name={JSON.stringify(customer)}
                value={editName}
                onChange={(e)=> changeName(e)}/>
                <label htmlFor="categoryName" className='form-label'>
                    Address
                </label>
                <input 
                type={"text"}
                className="form-control"
                name={JSON.stringify(customer)}
                value={editAddress}
                onChange={(e)=> changeAddress(e)}/>
                </div>
                <div className="modal-footer text-center">
                    <button type="button" className="btn btn-primary" style={{paddingLeft:20,paddingRight:20,marginRight:50}}
                    onClick={()=>editCustomer(customer.id)} data-dismiss="modal"
                    >Edit</button>
                    <button type="button" className="btn btn-secondary" onClick={()=>{setEditName([]); setEditAddress([])}} data-dismiss="modal" >Close</button>
                    
                </div>
                </div>
            </div>
            </div>
            <div className="modal fade" id="modal2" tabIndex="-1" role="dialog" aria-labelledby="modal2Label" aria-hidden="true">
            <div className="modal-dialog" role="document">
                <div className="modal-content">
                <div className="modal-header">
                    <h5 className="modal-title" id="modal2Label">Change Password : {customer.phoneNumber}</h5>
                    <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div className="modal-body">
                <label htmlFor="categoryName" className='form-label'>
                    Current Password
                </label>
                <input 
                type={"password"}
                className="form-control"
                value={password}

                onChange={(e)=> setPassword(e.target.value)}/>
                <div class="text-danger" hidden={isSubmitClicked!==true||password!==""}>Please fill out this field.</div>
                <label htmlFor="categoryName" className='form-label'>
                    New Password
                </label>
                <input 
                type={"password"}
                className="form-control"
                value={newPassword}
                onChange={(e)=> setNewPassword(e.target.value)}/>
                <div class="text-danger" hidden={isSubmitClicked!==true||newPassword!==""}>Please fill out this field.</div>
                <label htmlFor="categoryName" className='form-label'>
                    Retype Password
                </label>
                <input 
                type={"password"}
                className="form-control"
                value={retypePassword}
                onChange={(e)=> setRetypePassword(e.target.value)}/>
                <div class="text-danger" hidden={isSubmitClicked!==true||newPassword===""||newPassword===retypePassword}>Not match password</div>
            <div class="text-danger" hidden={isSubmitClicked!==true||retypePassword!==""}>Please fill out this field.</div>
                </div>
                <div className="modal-footer text-center">
                    <button type="button" className="btn btn-primary" style={{paddingLeft:20,paddingRight:20,marginRight:50}}
                    onClick={()=>changPassword(customer.id)} data-dismiss="modal"
                    >Change Password</button>
                    <button type="button" className="btn btn-secondary" 
                    onClick={()=>{setPassword([]);setNewPassword([]);setRetypePassword([])}} 
                    data-dismiss="modal" >Close</button>
                    
                </div>
                </div>
            </div>
            </div>
        </div>
    </div>
  )
}
