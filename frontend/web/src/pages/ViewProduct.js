import  { React,useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { Button, Image } from 'react-bootstrap';
import axios from 'axios';
import "../components/style.css";
import Rating from "../components/Rating";
import Cookies from 'universal-cookie';
export default function ViewProduct() {
    const cookies = new Cookies()
    const {id} = useParams()
    const [product, setProduct] = useState([])
    const [rating,setRating] = useState(5)
    const [quantity,setQuantity] = useState(1)
    useEffect(()=>{
      loadProduct();
    },[])
  
    const loadProduct = async()=>{
      const result=await axios.get(`http://localhost:8080/product/${id}`);
      setProduct(result.data);
      console.log(product)
    }
    const ratingProduct = ()=>{
      axios.post(`http://localhost:8080/product/${product.id}/rating`,{
        'id':{'customerId':cookies.get('customerId'),'productId':product.id},
        'point':rating
      }).then(()=>{
        alert("Thank You")
        loadProduct()
        setRating(5)
      }).catch((error)=>{
        console.log(error.response)
      })
    }
    const addToCart = async(e)=>{
      e.preventDefault()
      axios.post(`http://localhost:8080/product/${product.id}`,{
        'id':{'customerId':cookies.get('customerId'),'productId':product.id},'quantity':quantity
      }
      ,{
        headers:{
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + cookies.get('token'),
            'customerId': cookies.get('customerId')
        }
      })
      alert ("Added product to cart")
    }

  return (
    <div className='productContainer'>
        <div className='col-md-8 offset-md-2 border rounded p-4 mt-2'>
            
            <div className='d-flex'>
                <div className='d-flex row'>
                    <Image src={product.imageUrl} className="img-fluid" alt={product.name}/>
                    
                </div>
                <div style={{paddingLeft:100}}>

                    <p> {product.name}</p>
                    {product.status==true?(<span>Price: {product.price} đ</span>):(<span>No more sell</span>)}
                    <p> Origin: {product.origin}</p>
                    <Rating rating={product.rate}/>
                    
                    <Button data-toggle="modal" data-target="#modal1" disabled={cookies.get('role')!=="ROLE_USER"} > Rating this product</Button>
                    <div className='d-flex' style={{marginTop:10}}>
                    <input
                    style={{marginRight:5, width:200, height:50}}
                    type={"number"} 
                    className="form-control" 
                    value={quantity}
                    onChange={(e)=>setQuantity(e.target.value)}
                    ></input>
                    <Button variant='success' onClick={(e)=>addToCart(e)} disabled={cookies.get('role')!=="ROLE_USER"||product.status===false}>Add To Cart</Button>
                    </div>
                    <div className="modal fade" id="modal1" tabIndex="-1" role="dialog" aria-labelledby="modal1Label" aria-hidden="true">
                    <div className="modal-dialog" role="document">
                        <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="modal1Label">Rating Product : {product.name}</h5>
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div className="modal-body">
                        <label htmlFor="categoryName" className='form-label'>
                            Point
                        </label>
                        <Rating
                          rating={rating}
                          onClick={(i) =>
                            setRating(i+1)
                          }
                          style={{ cursor: "pointer" }}
                        />
                        </div>
                        <div className="modal-footer text-center">
                            <button type="button" className="btn btn-primary" style={{paddingLeft:20,paddingRight:20,marginRight:50}}
                             data-dismiss="modal"
                             onClick={()=>ratingProduct()}
                            >Rating</button>
                            <button type="button" className="btn btn-secondary"  data-dismiss="modal" >Close</button>
                        </div>
                        </div>
                    </div>
                    </div>      
                </div>
            </div>
            <div style={{paddingTop:20}}>
                <h2>Description</h2>
                <p>{product.description}</p>
            </div>
        </div>
    </div>
  )
}
