import  { React,useEffect, useState } from 'react'
import {Button,Image} from 'react-bootstrap'
import {Link} from 'react-router-dom'
import axios from 'axios'
import Cookies from 'universal-cookie'

export default function Order(){
    const cookies = new Cookies();
    const [products, setProducts] = useState([])
    useEffect(()=>{
        loadProducts();
      },[])
    const loadProducts = ()=>{
        axios.get("http://localhost:8080/product",{
          headers:{
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + cookies.get('token')
          }
      }).then((res)=>{
        setProducts(res.data);
      })}
    return(
        <div className='col-md-10 offset-md-1 border rounded p-4 mt-2'>
            <div className='text-center m-4'> 
                <Button style={{paddingLeft:100,paddingRight:100}}>Add product</Button>
            </div>
            <table className="table table-striped">
                <thead>
                    <tr>
                    <th scope="col" className='text-center m-4' style={{paddingLeft:150}}>#</th>
                    <th scope="col" className='text-center m-4'>Image</th>
                    <th scope="col" className='text-center m-4'>Product Name</th>
                    </tr>
                </thead>
                <tbody>
                {
                    products.sort((a, b) => a.id - b.id).map((product)=>(
                        <tr>
                            <th scope="row" className='text-center m-4' style={{paddingLeft:150}} key={product.id}>{product.id}</th>
                            <td className='text-center m-4'><Image src={product.imageUrl} alt={product.name} fluid width={100}/></td>
                            <td className='text-center m-4'>{product.name}</td>
                            <td className='text-center m-4'>
                                <Link to={`/product/${product.id}`}>
                                    <Button>View Detail</Button>
                                </Link>
                                <Link to={`/manager/product/edit/${product.id}`}>
                                    <Button variant="warning" style={{marginLeft:10,marginRight:10,paddingLeft:30,paddingRight:30}}>Edit</Button>
                                </Link>
                                {product.status?(
                                    <Button variant="danger" >Deactive</Button>
                                ):(
                                    <Button variant='success' >Active</Button>
                                )}
                                
                                
                            </td>
                        </tr>
                    )).reverse()
                }
                </tbody>
                
            </table>
        </div>
    )
}